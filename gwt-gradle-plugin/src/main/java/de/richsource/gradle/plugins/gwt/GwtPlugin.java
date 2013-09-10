package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.bundling.War;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;

public class GwtPlugin implements Plugin<Project> {
	public static final String CONFIGURATION_NAME = "gwt";
	public static final String EXTENSION_NAME = "gwt";
	public static final String BUILD_DIR = "gwt";
	public static final String OUT_DIR = "out";
	public static final String DRAFT_OUT_DIR = "draftOut";
	public static final String EXTRA_DIR = "extra";
	public static final String WORK_DIR = "work";
	public static final String GEN_DIR = "gen";
	
	private static final String DEV_WAR = "war";
	private static final String TASK_WAR_TEMPLATE = "warTemplate";
	public static final String TASK_COMPILE_GWT = "compileGwt";
	public static final String TASK_DRAFT_COMPILE_GWT = "draftCompileGwt";
	public static final String TASK_GWT_DEV = "gwtDev";
	public static final String TASK_GWT_SUPER_DEV = "gwtSuperDev";
	
	public static final String GWT_GROUP = "com.google.gwt";
	public static final String GWT_DEV = "gwt-dev";
	public static final String GWT_USER = "gwt-user";
	public static final String GWT_CODESERVER = "gwt-codeserver";
	public static final String GWT_SERVLET = "gwt-servlet";

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		
		final File buildDir = new File(project.getBuildDir(), BUILD_DIR);
		
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		extension.setDevWar(project.file(DEV_WAR));
		extension.setExtraDir(new File(buildDir, EXTRA_DIR));
		extension.setWorkDir(new File(buildDir, WORK_DIR));
		extension.setGenDir(new File(buildDir, GEN_DIR));
		
		configureAbstractActionTasks(project, extension);
		configureAbstractTasks(project, extension);
		configureGwtCompile(project, extension);
		configureGwtDev(project, extension);
		configureSuperGwtDev(project, extension);
		
		final Configuration gwtConfiguration = project.getConfigurations().create(CONFIGURATION_NAME);
		project.getConfigurations().getByName(JavaPlugin.COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
		
		final GwtCompile compileTask = project.getTasks().create(TASK_COMPILE_GWT, GwtCompile.class);
		compileTask.setWar(new File(buildDir, OUT_DIR));
		
		final GwtCompile draftCompileTask = project.getTasks().create(TASK_DRAFT_COMPILE_GWT, GwtCompile.class, new Action<GwtCompile>(){
			@Override
			public void execute(GwtCompile task) {
				task.setDevTask(true);
			}});
		draftCompileTask.setWar(new File(buildDir, DRAFT_OUT_DIR));
		draftCompileTask.setDraftCompile(true);
		
		final GwtSuperDev superDevTask = project.getTasks().create(TASK_GWT_SUPER_DEV, GwtSuperDev.class);
		superDevTask.setWorkDir(compileTask.getWorkDir());
		
		project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){

			@Override
			public void execute(WarPlugin warPlugin) {
				War warTask = (War) project.getTasks().getByName(WarPlugin.WAR_TASK_NAME);
				
				warTask.from(new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						return compileTask.getWar();
					}});
				
				project.getConfigurations().getByName(WarPlugin.PROVIDED_COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
				
				final Copy warTemplateTask = project.getTasks().create(TASK_WAR_TEMPLATE, Copy.class);
				warTemplateTask.with(warTask);
				warTemplateTask.conventionMapping("destinationDir", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getDevWar();
					}});
				
				final GwtDev devModeTask = project.getTasks().create(TASK_GWT_DEV, GwtDev.class);
				devModeTask.conventionMapping("war", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getDevWar();
					}});
				
				
				final War draftWar = project.getTasks().create("draftWar", War.class);
				draftWar.from(new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						return draftCompileTask.getWar();
					}});
				draftWar.dependsOn(draftCompileTask);
				draftWar.setBaseName(warTask.getBaseName()+"-draft");
				
				for(Object dependsTask:warTask.getDependsOn()) {
					devModeTask.dependsOn(dependsTask);
					// TODO dirty...
					superDevTask.dependsOn(dependsTask);
				}
				devModeTask.dependsOn(warTemplateTask);
				
				
				warTask.dependsOn(compileTask);
				
			}
		});
		
		configureEclipse(project);
		
		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(Project arg0) {
				final String gwtVersion = extension.getGwtVersion();
				if(gwtVersion != null && !extension.getGwtVersion().isEmpty()) {
					project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_DEV, gwtVersion));
					project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_USER, gwtVersion));
					project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_CODESERVER, gwtVersion));
					project.getDependencies().add(JavaPlugin.RUNTIME_CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_SERVLET, gwtVersion));
				}
			}});
		
	}

	private void configureAbstractTasks(final Project project,
			final GwtPluginExtension extension) {
		project.getTasks().withType(AbstractGwtTask.class, new Action<AbstractGwtTask>() {
			@Override
			public void execute(final AbstractGwtTask task) {
				task.conventionMapping("extra", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getExtraDir();
					}});
				task.conventionMapping("workDir", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getWorkDir();
					}});
				task.conventionMapping("gen", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getGenDir();
					}});
				task.conventionMapping("cacheDir", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getCacheDir();
					}});
				task.conventionMapping("logLevel", new Callable<LogLevel>(){
					@Override
					public LogLevel call() throws Exception {
						return extension.getLogLevel();
					}});
			}});
	}
	
	private void configureAbstractActionTasks(final Project project,
			final GwtPluginExtension extension) {
		final JavaPluginConvention javaConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
		final SourceSet mainSourceSet = javaConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
		project.getTasks().withType(AbstractGwtActionTask.class, new Action<AbstractGwtActionTask>() {
			@Override
			public void execute(final AbstractGwtActionTask task) {
				task.conventionMapping("modules", new Callable<List<String>>() {
					@Override
					public List<String> call() throws Exception {
						final List<String> devModules = extension.getDevModules();
						if(task.isDevTask() && devModules!= null && !devModules.isEmpty()) {
							return devModules;
						}
						return extension.getModules();
					}
				});
				task.conventionMapping("src", new Callable<Set<File>>() {
					@Override
					public Set<File> call() throws Exception {
						final Set<File> src = new HashSet<File>();
						src.addAll(mainSourceSet.getAllJava().getSrcDirs());
						src.add(mainSourceSet.getOutput().getResourcesDir());
						return src;
					}
				});
				task.conventionMapping("classpath", new Callable<FileCollection>() {
					@Override
					public FileCollection call() throws Exception {
						return mainSourceSet.getCompileClasspath().plus(project.files(mainSourceSet.getOutput().getClassesDir()));
					}
				});
				task.conventionMapping("minHeapSize", new Callable<String>() {
					@Override
					public String call() throws Exception {
						return extension.getMinHeapSize();
					}
				});
				task.conventionMapping("maxHeapSize", new Callable<String>() {
					@Override
					public String call() throws Exception {
						return extension.getMaxHeapSize();
					}
				});
			}});
	}
	
	private void configureGwtCompile(final Project project,
			final GwtPluginExtension extension) {
		project.getTasks().withType(GwtCompile.class, new Action<GwtCompile>() {
			@Override
			public void execute(final GwtCompile task) {
				task.conventionMapping("localWorkers", new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return Runtime.getRuntime().availableProcessors();
					}
				});
			}
		});
	}

	private void configureGwtDev(final Project project, final GwtPluginExtension extension) {
		project.getTasks().withType(GwtDev.class, new Action<GwtDev>() {
			@Override
			public void execute(final GwtDev task) {
				task.configure(extension.getDev());
			}
		});
	}
	
	private void configureSuperGwtDev(final Project project, final GwtPluginExtension extension) {
		project.getTasks().withType(GwtSuperDev.class, new Action<GwtSuperDev>() {
			@Override
			public void execute(final GwtSuperDev task) {
				task.configure(extension.getSuperDev());
			}
		});
	}

	private void configureEclipse(final Project project) {
		project.getPlugins().withType(EclipsePlugin.class, new Action<EclipsePlugin>(){
			@Override
			public void execute(EclipsePlugin eclipsePlugin) {
				final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
				eclipseModel.getProject().natures("com.google.gwt.eclipse.core.gwtNature");
				eclipseModel.getProject().buildCommand("com.google.gwt.eclipse.core.gwtProjectValidator");
				
				project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){
					@Override
					public void execute(WarPlugin warPlugin) {
						eclipseModel.getProject().buildCommand("com.google.gdt.eclipse.core.webAppProjectValidator");
						
						project.getTasks().getByName(EclipsePlugin.getECLIPSE_PROJECT_TASK_NAME()).dependsOn(TASK_WAR_TEMPLATE);
					}
				});
			}});
	}
}
