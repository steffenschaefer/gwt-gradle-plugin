/**
 * Copyright (C) 2013 Steffen Schaefer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ExcludeRule;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.bundling.War;
import org.gradle.api.tasks.testing.Test;
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
	public static final String LOG_DIR = "log";
	
	public static final String DEV_WAR = "war";
	
	public static final String TASK_WAR_TEMPLATE = "warTemplate";
	public static final String TASK_COMPILE_GWT = "compileGwt";
	public static final String TASK_DRAFT_COMPILE_GWT = "draftCompileGwt";
	public static final String TASK_DRAFT_WAR = "draftWar";
	public static final String TASK_GWT_DEV = "gwtDev";
	public static final String TASK_GWT_SUPER_DEV = "gwtSuperDev";
	
	public static final String GWT_GROUP = "com.google.gwt";
	public static final String GWT_DEV = "gwt-dev";
	public static final String GWT_USER = "gwt-user";
	public static final String GWT_CODESERVER = "gwt-codeserver";
	public static final String GWT_ELEMENTAL = "gwt-elemental";
	public static final String GWT_SERVLET = "gwt-servlet";

	public static final String ECLIPSE_NATURE = "com.google.gwt.eclipse.core.gwtNature";
	public static final String ECLIPSE_BUILDER_PROJECT_VALIDATOR = "com.google.gwt.eclipse.core.gwtProjectValidator";
	public static final String ECLIPSE_BUILDER_WEBAPP_VALIDATOR = "com.google.gdt.eclipse.core.webAppProjectValidator";
	public static final String ECLIPSE_GWT_CONTAINER = "com.google.gwt.eclipse.core.GWT_CONTAINER";
	
	private static final Logger logger = Logging.getLogger(GwtPlugin.class);

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		
		final File buildDir = new File(project.getBuildDir(), BUILD_DIR);
		
		final JavaPluginConvention javaConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
		final SourceSet mainSourceSet = javaConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
		
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		extension.setDevWar(project.file(DEV_WAR));
		extension.setExtraDir(new File(buildDir, EXTRA_DIR));
		extension.setWorkDir(new File(buildDir, WORK_DIR));
		extension.setGenDir(new File(buildDir, GEN_DIR));
		extension.getDev().setLogDir(new File(buildDir, LOG_DIR));
		extension.getCompiler().setLocalWorkers(Runtime.getRuntime().availableProcessors());
		extension.setSrc(project.files(mainSourceSet.getAllJava().getSrcDirs()).plus(project.files(mainSourceSet.getOutput().getResourcesDir())));
		
		configureAbstractActionTasks(project, extension);
		configureAbstractTasks(project, extension);
		configureGwtCompile(project, extension);
		configureGwtDev(project, extension);
		configureGwtSuperDev(project, extension);
		
		final Configuration gwtConfiguration = project.getConfigurations().create(CONFIGURATION_NAME);
//		project.getConfigurations().getByName(JavaPlugin.COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
		
		mainSourceSet.setCompileClasspath(mainSourceSet.getCompileClasspath().plus(gwtConfiguration));
		
		final GwtCompile compileTask = project.getTasks().create(TASK_COMPILE_GWT, GwtCompile.class);
		compileTask.setWar(new File(buildDir, OUT_DIR));
		compileTask.setDescription("Runs the GWT compiler to translate Java sources to JavaScript for production ready output");
		
		final GwtDraftCompile draftCompileTask = project.getTasks().create(TASK_DRAFT_COMPILE_GWT, GwtDraftCompile.class);
		draftCompileTask.setWar(new File(buildDir, DRAFT_OUT_DIR));
		draftCompileTask.setDescription("Runs the GWT compiler to produce draft quality output used for development");
		
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
				warTemplateTask.setDescription("Creates a exploded webapplication template to be used by GWT dev mode and eclipse to ensure src/main/webapp stays clean");
				
				final GwtDev devModeTask = project.getTasks().create(TASK_GWT_DEV, GwtDev.class);
				devModeTask.conventionMapping("war", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getDevWar();
					}});
				
				
				final War draftWar = project.getTasks().create(TASK_DRAFT_WAR, War.class);
				draftWar.from(new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						return draftCompileTask.getWar();
					}});
				draftWar.dependsOn(draftCompileTask);
				draftWar.setBaseName(warTask.getBaseName()+"-draft");
				draftWar.setDescription("Creates a war using the output of the task "+TASK_DRAFT_COMPILE_GWT);
				
				for(Object dependsTask:warTask.getDependsOn()) {
					devModeTask.dependsOn(dependsTask);
				}
				devModeTask.dependsOn(warTemplateTask);
				
				
				warTask.dependsOn(compileTask);
				
			}
		});
		
		configureEclipse(project, extension);
		
		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(final Project project) {
				boolean versionSet = false;
				int major = 2;
				int minor = 5;
				
				final String gwtVersion = extension.getGwtVersion();
				if(gwtVersion != null && !extension.getGwtVersion().isEmpty()) {
					final String[] token = gwtVersion.split("\\.");
					if(token.length>=2) {
						try {
							major = Integer.parseInt(token[0]);
							minor = Integer.parseInt(token[1]);
							versionSet = true;
						} catch(NumberFormatException e) {
							logger.warn("GWT version "+extension.getGwtVersion()+" can not be parsed. Valid versions must have the format major.minor.patch where major and minor are positive integer numbers.");
						}
					} else {
						logger.warn("GWT version "+extension.getGwtVersion()+" can not be parsed. Valid versions must have the format major.minor.patch where major and minor are positive integer numbers.");
					}
				}
				
				if ((major == 2 && minor >= 5) || major > 2) {
					if(extension.isCodeserver()) {
						createSuperDevModeTask(project);
					}
				}
				
				if(versionSet) {
					project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_DEV, gwtVersion));
					project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_USER, gwtVersion));
					project.getDependencies().add(JavaPlugin.RUNTIME_CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_SERVLET, gwtVersion));
					
					if ((major == 2 && minor >= 5) || major > 2) {
						if(extension.isCodeserver()) {
							project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_CODESERVER, gwtVersion));
						}
						if(extension.isElemental()) {
							project.getDependencies().add(CONFIGURATION_NAME, new DefaultExternalModuleDependency(GWT_GROUP, GWT_ELEMENTAL, gwtVersion));
						}
					} else {
						logger.warn("GWT version is <2.5 -> additional dependencies are not added.");
					}
				}
				
				if(extension.getEclipse().isAddGwtContainer()) {
					project.getPlugins().withType(EclipsePlugin.class, new Action<EclipsePlugin>(){
						@Override
						public void execute(EclipsePlugin eclipsePlugin) {
							final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
							
							eclipseModel.getClasspath().getContainers().add(ECLIPSE_GWT_CONTAINER);
							
							Collection<Configuration> configurations = eclipseModel.getClasspath().getPlusConfigurations();
							for (Configuration configuration : configurations) {
								configuration.exclude(Collections.singletonMap(ExcludeRule.GROUP_KEY, GWT_GROUP));
							}
						}
					});
				}
				
			}});
		
		final SourceSet testSourceSet = javaConvention.getSourceSets().getByName(SourceSet.TEST_SOURCE_SET_NAME);
		testSourceSet.setCompileClasspath(testSourceSet.getCompileClasspath().plus(gwtConfiguration));
		testSourceSet.setRuntimeClasspath(
				project.files(
						mainSourceSet.getAllJava().getSrcDirs().toArray())
						.plus(project.files(testSourceSet.getAllJava()
								.getSrcDirs().toArray()))
				.plus(gwtConfiguration).plus(testSourceSet
				.getRuntimeClasspath()));
		
		project.getTasks().withType(Test.class, new Action<Test>() {
			@Override
			public void execute(final Test testTask) {
				testTask.getTestLogging().setShowStandardStreams(true);
				
				final GwtTestExtension testExtension = testTask.getExtensions().create("gwt", GwtTestExtension.class);
				testExtension.configure(extension, (IConventionAware) testExtension);

				testTask.doFirst(new Action<Task>() {
					@Override
					public void execute(Task arg0) {
						String gwtArgs = testExtension.getParameterString();
						testTask.systemProperty("gwt.args", gwtArgs);
						logger.info("Using gwt.args for test: "+ gwtArgs);

						if (testExtension.getCacheDir() != null) {
							testTask.systemProperty("gwt.persistentunitcachedir", testExtension.getCacheDir());
							testExtension.getCacheDir().mkdirs();
							logger.info("Using gwt.persistentunitcachedir for test: {0}", testExtension.getCacheDir());
						}
					}
				});
			}
		});
	}


	private void createSuperDevModeTask(final Project project) {
		final GwtSuperDev superDevTask = project.getTasks().create(TASK_GWT_SUPER_DEV, GwtSuperDev.class);
		superDevTask.dependsOn(JavaPlugin.COMPILE_JAVA_TASK_NAME, JavaPlugin.PROCESS_RESOURCES_TASK_NAME);
		superDevTask.setDescription("Runs the GWT super dev mode");
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
				task.conventionMapping("src", new Callable<FileCollection>() {
					@Override
					public FileCollection call() throws Exception {
						return extension.getSrc();
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
		project.getTasks().withType(AbstractGwtCompile.class, new Action<AbstractGwtCompile>() {
			@Override
			public void execute(final AbstractGwtCompile task) {
				task.configure(extension.getCompiler());
			}
		});
	}

	private void configureGwtDev(final Project project, final GwtPluginExtension extension) {
		final boolean debug = "true".equals(System.getProperty("gwtDev.debug"));
		project.getTasks().withType(GwtDev.class, new Action<GwtDev>() {
			@Override
			public void execute(final GwtDev task) {
				task.configure(extension.getDev());
				task.setDebug(debug);
			}
		});
	}
	
	private void configureGwtSuperDev(final Project project, final GwtPluginExtension extension) {
		project.getTasks().withType(GwtSuperDev.class, new Action<GwtSuperDev>() {
			@Override
			public void execute(final GwtSuperDev task) {
				task.configure(extension.getSuperDev());
				task.conventionMapping("workDir", new Callable<File>() {

					@Override
					public File call() throws Exception {
						return extension.getWorkDir();
					}});
			}
		});
	}

	private void configureEclipse(final Project project, final GwtPluginExtension extension) {
		project.getPlugins().withType(EclipsePlugin.class, new Action<EclipsePlugin>(){
			@Override
			public void execute(EclipsePlugin eclipsePlugin) {
				final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
				eclipseModel.getProject().natures(ECLIPSE_NATURE);
				eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_PROJECT_VALIDATOR);
				
				project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){
					@Override
					public void execute(WarPlugin warPlugin) {
						eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_WEBAPP_VALIDATOR);
						
						project.getTasks().getByName(EclipsePlugin.getECLIPSE_TASK_NAME()).dependsOn(TASK_WAR_TEMPLATE);
						project.getTasks().getByName(getAssociatedCleanTask(EclipsePlugin.getECLIPSE_TASK_NAME())).dependsOn(getAssociatedCleanTask(TASK_WAR_TEMPLATE));
						
						project.afterEvaluate(new Action<Project>() {
							@Override
							public void execute(final Project project) {
								final File devWar = extension.getDevWar();
								final File classes = new File(devWar, "WEB-INF/classes");
								eclipseModel.getClasspath().setDefaultOutputDir(classes);
							}
						});
					}
				});
			}});
	}
	
	private String getAssociatedCleanTask(final String taskName) {
		final String first = taskName.substring(0, 1).toUpperCase();
		final String rest = taskName.substring(1);
		return "clean"+first+rest;
	}
}
