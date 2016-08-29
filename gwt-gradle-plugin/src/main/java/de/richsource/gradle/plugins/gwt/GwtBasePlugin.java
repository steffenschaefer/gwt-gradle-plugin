/**
 * Copyright (C) 2013-2016 Steffen Schaefer
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
import java.util.List;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.testing.Test;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.idea.IdeaPlugin;

public class GwtBasePlugin implements Plugin<Project> {
	public static final String GWT_TASK_GROUP = "GWT";
	
	public static final String GWT_CONFIGURATION = "gwt";
	public static final String GWT_SDK_CONFIGURATION = "gwtSdk";
	public static final String EXTENSION_NAME = "gwt";
	public static final String BUILD_DIR = "gwt";
	public static final String EXTRA_DIR = "extra";
	public static final String WORK_DIR = "work";
	public static final String GEN_DIR = "gen";
	public static final String CACHE_DIR = "cache";
	public static final String LOG_DIR = "log";
	
	public static final String DEV_WAR = "war";
	
	public static final String TASK_GWT_SUPER_DEV = "gwtSuperDev";
	
	public static final String GWT_GROUP = "com.google.gwt";
	public static final String GWT_DEV = "gwt-dev";
	public static final String GWT_USER = "gwt-user";
	public static final String GWT_CODESERVER = "gwt-codeserver";
	public static final String GWT_ELEMENTAL = "gwt-elemental";
	public static final String GWT_SERVLET = "gwt-servlet";
	
	private static final Logger logger = Logging.getLogger(GwtBasePlugin.class);
	private Project project;
	private GwtPluginExtension extension;
	private Configuration gwtConfiguration;
	private Configuration gwtSdkConfiguration;
	private ConfigurableFileCollection allGwtConfigurations;

	@Override
	public void apply(final Project project) {
		this.project = project;
		project.getPlugins().apply(JavaPlugin.class);
		
		final File gwtBuildDir = new File(project.getBuildDir(), BUILD_DIR);
		
		extension = configureGwtExtension(gwtBuildDir);
		
		configureAbstractActionTasks();
		configureAbstractTasks();
		configureGwtCompile();
		configureGwtDev();
		configureGwtSuperDev();
		
		gwtConfiguration = project.getConfigurations().create(GWT_CONFIGURATION)
				.setDescription("Classpath for GWT client libraries that are not included in the war");
		gwtSdkConfiguration = project.getConfigurations().create(GWT_SDK_CONFIGURATION)
				.setDescription("Classpath for GWT SDK libraries (gwt-dev, gwt-user)");
		allGwtConfigurations = project.files(gwtConfiguration, gwtSdkConfiguration);
		
		addToMainSourceSetClasspath(allGwtConfigurations);
		
		final SourceSet testSourceSet = getTestSourceSet();
		testSourceSet.setCompileClasspath(testSourceSet.getCompileClasspath().plus(allGwtConfigurations));
		
		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(final Project project) {
				FileCollection runtimeClasspath = allGwtConfigurations.plus(testSourceSet
										.getRuntimeClasspath());
				if(extension.getTest().isHasGwtTests()) {
					runtimeClasspath = project.files(
						getMainSourceSet().getAllJava().getSrcDirs().toArray())
						.plus(project.files(testSourceSet.getAllJava()
								.getSrcDirs().toArray())).plus(runtimeClasspath);
					
					configureTestTasks(extension);
				}
				testSourceSet.setRuntimeClasspath(runtimeClasspath);
				
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
					project.getDependencies().add(GWT_SDK_CONFIGURATION, gwtDependency(GWT_DEV, gwtVersion));
					project.getDependencies().add(GWT_SDK_CONFIGURATION, gwtDependency(GWT_USER, gwtVersion));
					project.getDependencies().add(JavaPlugin.RUNTIME_CONFIGURATION_NAME, gwtDependency(GWT_SERVLET, gwtVersion));
					
					if ((major == 2 && minor >= 5) || major > 2) {
						if(extension.isCodeserver()) {
							project.getDependencies().add(GWT_CONFIGURATION, gwtDependency(GWT_CODESERVER, gwtVersion));
						}
						if(extension.isElemental()) {
							project.getDependencies().add(GWT_CONFIGURATION, gwtDependency(GWT_ELEMENTAL, gwtVersion));
						}
					} else {
						logger.warn("GWT version is <2.5 -> additional dependencies are not added.");
					}
				}
				
			}});

		project.getPlugins().withType(EclipsePlugin.class,
				new Action<EclipsePlugin>() {
					@Override
					public void execute(EclipsePlugin eclipsePlugin) {
						new GwtEclipsePlugin().apply(project, GwtBasePlugin.this);
					}
				});

		project.getPlugins().withType(IdeaPlugin.class,
				new Action<IdeaPlugin>() {
					@Override
					public void execute(IdeaPlugin ideaPlugin) {
						new GwtIdeaPlugin().apply(project, GwtBasePlugin.this);
					}
				});
	}
	
	private String gwtDependency(final String artifactId, final String gwtVersion) {
		return GWT_GROUP+":"+artifactId+":"+gwtVersion;
	}

	private GwtPluginExtension configureGwtExtension(final File buildDir) {
		
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		extension.setDevWar(project.file(DEV_WAR));
		extension.setExtraDir(new File(buildDir, EXTRA_DIR));
		extension.setWorkDir(new File(buildDir, WORK_DIR));
		extension.setGenDir(new File(buildDir, GEN_DIR));
		extension.setCacheDir(new File(buildDir, CACHE_DIR));
		extension.getDev().setLogDir(new File(buildDir, LOG_DIR));
		extension.getCompiler().setLocalWorkers(Runtime.getRuntime().availableProcessors());
		extension.setLogLevel(getLogLevel());
		extension.getSuperDev().setUseClasspathForSrc(true);
		
		ConventionMapping conventionMapping = ((IConventionAware)extension).getConventionMapping();
		conventionMapping.map("src", new Callable<FileCollection>(){
			@Override
			public FileCollection call() throws Exception {
				final SourceSet mainSourceSet = getMainSourceSet();
				return project.files(mainSourceSet.getAllJava().getSrcDirs()).plus(project.files(mainSourceSet.getOutput().getResourcesDir()));
			}});
		
		return extension;
	}


	private void createSuperDevModeTask(final Project project) {
		final GwtSuperDev superDevTask = project.getTasks().create(TASK_GWT_SUPER_DEV, GwtSuperDev.class);
		superDevTask.dependsOn(JavaPlugin.COMPILE_JAVA_TASK_NAME, JavaPlugin.PROCESS_RESOURCES_TASK_NAME);
		superDevTask.setDescription("Runs the GWT super dev mode");
	}

	private void configureAbstractTasks() {
		project.getTasks().withType(AbstractGwtTask.class, new Action<AbstractGwtTask>() {
			@Override
			public void execute(final AbstractGwtTask task) {
				ConventionMapping conventionMapping = ((IConventionAware)task).getConventionMapping();
				conventionMapping.map("extra", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getExtraDir();
					}});
				conventionMapping.map("workDir", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getWorkDir();
					}});
				conventionMapping.map("gen", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getGenDir();
					}});
				conventionMapping.map("cacheDir", new Callable<File>(){
					@Override
					public File call() throws Exception {
						return extension.getCacheDir();
					}});
				// TODO logLevel was introduced to CodeServer in GWT 2.7
				// To not break compatibility with previous versions the conventionMapping is not applied for gwtSuperDev task
				// There should be GWT version depending configuration
				conventionMapping.map("logLevel", new Callable<LogLevel>(){
					@Override
					public LogLevel call() throws Exception {
						return extension.getLogLevel();
					}});
			}});
	}
	
	private void configureAbstractActionTasks() {
		final JavaPluginConvention javaConvention = getJavaConvention();
		final SourceSet mainSourceSet = javaConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
		project.getTasks().withType(AbstractGwtActionTask.class, new Action<AbstractGwtActionTask>() {
			@Override
			public void execute(final AbstractGwtActionTask task) {
				task.setGroup(GwtBasePlugin.GWT_TASK_GROUP);
				
				ConventionMapping conventionMapping = ((IConventionAware)task).getConventionMapping();
				conventionMapping.map("modules", new Callable<List<String>>() {
					@Override
					public List<String> call() throws Exception {
						final List<String> devModules = extension.getDevModules();
						if(task.isDevTask() && devModules!= null && !devModules.isEmpty()) {
							return devModules;
						}
						return extension.getModules();
					}
				});
				conventionMapping.map("src", new Callable<FileCollection>() {
					@Override
					public FileCollection call() throws Exception {
						return extension.getSrc();
					}
				});
				conventionMapping.map("classpath", new Callable<FileCollection>() {
					@Override
					public FileCollection call() throws Exception {
						return mainSourceSet.getCompileClasspath().plus(project.files(mainSourceSet.getOutput().getClassesDir()));
					}
				});
				conventionMapping.map("minHeapSize", new Callable<String>() {
					@Override
					public String call() throws Exception {
						return extension.getMinHeapSize();
					}
				});
				conventionMapping.map("maxHeapSize", new Callable<String>() {
					@Override
					public String call() throws Exception {
						return extension.getMaxHeapSize();
					}
				});
				conventionMapping.map("sourceLevel", new Callable<String>() {
					@Override
					public String call() throws Exception {
						return extension.getSourceLevel();
					}
				});
				conventionMapping.map("incremental", new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return extension.getIncremental();
					}
				});
				conventionMapping.map("jsInteropMode", new Callable<JsInteropMode>() {
					@Override
					public JsInteropMode call() throws Exception {
						return extension.getJsInteropMode();
					}
				});
				conventionMapping.map("generateJsInteropExports", extension::getGenerateJsInteropExports);
				conventionMapping.map("methodNameDisplayMode", extension::getMethodNameDisplayMode);
			}});
	}
	
	private void configureGwtCompile() {
		project.getTasks().withType(AbstractGwtCompile.class, new Action<AbstractGwtCompile>() {
			@Override
			public void execute(final AbstractGwtCompile task) {
				task.configure(extension.getCompiler());
			}
		});
	}

	private void configureGwtDev() {
		final boolean debug = "true".equals(System.getProperty("gwtDev.debug"));
		project.getTasks().withType(GwtDev.class, new Action<GwtDev>() {
			@Override
			public void execute(final GwtDev task) {
				task.configure(extension);
				task.setDebug(debug);
			}
		});
	}
	
	private void configureGwtSuperDev() {
		project.getTasks().withType(GwtSuperDev.class, new Action<GwtSuperDev>() {
			@Override
			public void execute(final GwtSuperDev task) {
				task.configure(extension.getSuperDev());
				ConventionMapping conventionMapping = ((IConventionAware)task).getConventionMapping();
				conventionMapping.map("workDir", new Callable<File>() {

					@Override
					public File call() throws Exception {
						return extension.getWorkDir();
					}});
			}
		});
	}
	
	private void configureTestTasks(final GwtPluginExtension gwtPluginExtension) {
		project.getTasks().withType(Test.class, new Action<Test>() {
			@Override
			public void execute(final Test testTask) {
				testTask.getTestLogging().setShowStandardStreams(true);
				
				final GwtTestExtension testExtension = testTask.getExtensions().create("gwt", GwtTestExtension.class);
				testExtension.configure(gwtPluginExtension, (IConventionAware) testExtension);
				
				project.afterEvaluate(new Action<Project>() {
					@Override
					public void execute(Project t) {
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
				
				project.getPlugins().withType(GwtWarPlugin.class, new Action<GwtWarPlugin>() {
					@Override
					public void execute(GwtWarPlugin t) {
						testTask.dependsOn(GwtWarPlugin.TASK_WAR_TEMPLATE);
					}});
			}
		});
	}
	
	private LogLevel getLogLevel() {
		if(logger.isTraceEnabled()) {
			return LogLevel.TRACE;
		} else if(logger.isDebugEnabled()) {
			return LogLevel.DEBUG;
		} else if(logger.isInfoEnabled()) {
			return LogLevel.INFO;
		} else if(logger.isLifecycleEnabled() || logger.isWarnEnabled()) {
			return LogLevel.WARN;
		}
		// QUIET or ERROR
		return LogLevel.ERROR;
	}
	
	private SourceSet getMainSourceSet() {
		return getJavaConvention().getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
	}
	
	private SourceSet getTestSourceSet() {
		return getJavaConvention().getSourceSets().getByName(SourceSet.TEST_SOURCE_SET_NAME);
	}

	private JavaPluginConvention getJavaConvention() {
		return project.getConvention().getPlugin(JavaPluginConvention.class);
	}
	
	private void addToMainSourceSetClasspath(FileCollection fileCollection) {
		final SourceSet mainSourceSet = getMainSourceSet();
		mainSourceSet.setCompileClasspath(getMainSourceSet().getCompileClasspath().plus(fileCollection));
	}
	
	GwtPluginExtension getExtension() {
		return extension;
	}
	
	Configuration getGwtConfiguration() {
		return gwtConfiguration;
	}
	
	Configuration getGwtSdkConfiguration() {
		return gwtSdkConfiguration;
	}
	
	ConfigurableFileCollection getAllGwtConfigurations() {
		return allGwtConfigurations;
	}
}
