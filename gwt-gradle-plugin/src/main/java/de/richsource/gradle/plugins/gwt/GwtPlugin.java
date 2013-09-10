package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.bundling.War;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;

import de.richsource.gradle.plugins.gwt.internal.HasDirs;

public class GwtPlugin implements Plugin<Project> {
	public static final String CONFIGURATION_NAME = "gwt";
	public static final String EXTENSION_NAME = "gwt";
	public static final String BUILD_DIR = "gwt";
	public static final String OUT_DIR = "out";
	public static final String EXTRA_DIR = "extra";
	public static final String WORK_DIR = "work";
	
	private static final String TASK_WAR_TEMPLATE = "warTemplate";
	
	public static final String GWT_GROUP = "com.google.gwt";
	public static final String GWT_DEV = "gwt-dev";
	public static final String GWT_USER = "gwt-user";
	public static final String GWT_CODESERVER = "gwt-codeserver";
	public static final String GWT_SERVLET = "gwt-servlet";

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		
		final Configuration gwtConfiguration = project.getConfigurations().create(CONFIGURATION_NAME);
		project.getConfigurations().getByName(JavaPlugin.COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
		
		final JavaPluginConvention javaConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
		final SourceSet mainSourceSet = javaConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
		
		final File buildDir = new File(project.getBuildDir(), BUILD_DIR);
		final File outDir = new File(buildDir, OUT_DIR);
		final File draftOutDir = new File(buildDir, "draftOut");
		final File extraDir = new File(buildDir, EXTRA_DIR);
		final File workDir = new File(buildDir, WORK_DIR);
		// configurable / convention mapping?
		final File warDir = project.file("war");
		
		final Set<File> src = new HashSet<File>();
		src.addAll(mainSourceSet.getAllJava().getSrcDirs());
		src.add(mainSourceSet.getOutput().getResourcesDir());
		final FileCollection compileClasspath = mainSourceSet.getCompileClasspath().plus(project.files(mainSourceSet.getOutput().getClassesDir()));
		
		final GwtCompile compileTask = project.getTasks().create(GwtCompile.NAME, GwtCompile.class);
		configureDirs(compileTask, outDir, extraDir, workDir);
		configureSrcAndClasspath(compileTask, src, compileClasspath);
		configureModules(extension, compileTask, false);
		
		final GwtCompile draftCompileTask = project.getTasks().create("draftCompileGwt", GwtCompile.class);
		configureDirs(draftCompileTask, draftOutDir, extraDir, workDir);
		configureSrcAndClasspath(draftCompileTask, src, compileClasspath);
		configureModules(extension, draftCompileTask, true);
		draftCompileTask.setDraftCompile(true);
		
		final GwtSuperDev superDevTask = configureSuperDevMode(project, extension, compileTask.getWorkDir(), src, compileClasspath);
		
		project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){

			@Override
			public void execute(WarPlugin warPlugin) {
				War warTask = (War) project.getTasks().getByName(WarPlugin.WAR_TASK_NAME);
				
				// TODO convention mapping
				warTask.from(compileTask.getWar());
				
				project.getConfigurations().getByName(WarPlugin.PROVIDED_COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
				
				final Copy warTemplateTask = project.getTasks().create(TASK_WAR_TEMPLATE, Copy.class);
				warTemplateTask.with(warTask);
				warTemplateTask.into(warDir);
				
				final GwtDev devModeTask = project.getTasks().create("gwtDev", GwtDev.class);
				configureDirs(devModeTask, warDir, extraDir, workDir);
				configureSrcAndClasspath(devModeTask, src, compileClasspath);
				configureNeverUpToDate(devModeTask);
				configureModules(extension, devModeTask, true);
				
				
				final War draftWar = project.getTasks().create("draftWar", War.class);
				// TODO convention mapping
				draftWar.from(draftCompileTask.getWar());
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

	private void configureDirs(final HasDirs task, final File outDir,
			final File extraDir, final File workDir) {
		task.setWar(outDir);
		task.setExtra(extraDir);
		task.setWorkDir(workDir);
	}

	private void configureSrcAndClasspath(final AbstractGwtActionTask task, final Set<File> src, final FileCollection classpath) {
		task.setSrc(src);
		task.setClasspath(classpath);
	}
	
	private void configureNeverUpToDate(Task devModeTask) {
		devModeTask.getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}

	private GwtSuperDev configureSuperDevMode(final Project project,
			final GwtPluginExtension extension, final File workDir,
			final Set<File> src, final FileCollection classpath) {
		GwtSuperDev superDevTask = project.getTasks().create("gwtSuperDev", GwtSuperDev.class);
		superDevTask.setWorkDir(workDir);
		
		configureSrcAndClasspath(superDevTask, src, classpath);
		configureModules(extension, superDevTask, true);
		
		return superDevTask;
	}

	private void configureModules(final GwtPluginExtension extension,
			final AbstractGwtActionTask task, final boolean dev) {
		task.conventionMapping("modules", new Callable<List<String>>() {

			@Override
			public List<String> call() throws Exception {
				final List<String> devModules = extension.getDevModules();
				if(dev && devModules!= null && !devModules.isEmpty()) {
					return devModules;
				}
				return extension.getModules();
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
