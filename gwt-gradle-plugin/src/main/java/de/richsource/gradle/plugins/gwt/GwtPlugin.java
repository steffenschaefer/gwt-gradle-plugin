package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.bundling.War;

public class GwtPlugin implements Plugin<Project> {
	public static final String CONFIGURATION_NAME = "gwt";
	public static final String EXTENSION_NAME = "gwt";
	public static final String BUILD_DIR = "gwt";
	public static final String OUT_DIR = "out";
	public static final String EXTRA_DIR = "extra";
	public static final String WORK_DIR = "work";

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		
		final Configuration gwtConfiguration = project.getConfigurations().create(CONFIGURATION_NAME);
		
		final GwtCompileTask compileTask = project.getTasks().create(GwtCompileTask.NAME, GwtCompileTask.class);
		final File buildDir = new File(project.getBuildDir(), BUILD_DIR);
		final File outDir = new File(buildDir, OUT_DIR);
		final File extraDir = new File(buildDir, EXTRA_DIR);
		final File workDir = new File(buildDir, WORK_DIR);
		compileTask.setWar(outDir);
		compileTask.setExtra(extraDir);
		compileTask.setWorkDir(workDir);
		
		final JavaPluginConvention javaConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
		SourceSet mainSourceSet = javaConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
		compileTask.classpath(mainSourceSet.getAllJava().getSrcDirs());
		compileTask.classpath(mainSourceSet.getOutput().getResourcesDir());
		compileTask.classpath(mainSourceSet.getOutput().getClassesDir());
		compileTask.classpath(mainSourceSet.getCompileClasspath());
		
		compileTask.getInputs().source(mainSourceSet.getAllJava().getSrcDirs());
		compileTask.getInputs().source(mainSourceSet.getOutput().getResourcesDir());
		
		compileTask.conventionMapping("modules", new Callable<List<String>>() {

			@Override
			public List<String> call() throws Exception {
				return extension.getModules();
			}
		});
		
		project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){

			@Override
			public void execute(WarPlugin warPlugin) {
				War warTask = (War) project.getTasks().getByName(WarPlugin.WAR_TASK_NAME);
				
				warTask.dependsOn(compileTask);
				warTask.from(compileTask.getWar());
				
				project.getConfigurations().getByName(WarPlugin.PROVIDED_COMPILE_CONFIGURATION_NAME).extendsFrom(gwtConfiguration);
			}});
		
		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(Project arg0) {
				final String gwtVersion = extension.getGwtVersion();
				if(gwtVersion != null && !extension.getGwtVersion().isEmpty()) {
					
					project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){

						@Override
						public void execute(WarPlugin warPlugin) {
							project.getDependencies().add(WarPlugin.PROVIDED_COMPILE_CONFIGURATION_NAME, "com.google.gwt:gwt-dev:"+gwtVersion);
							project.getDependencies().add(WarPlugin.PROVIDED_COMPILE_CONFIGURATION_NAME, "com.google.gwt:gwt-user:"+gwtVersion);
							project.getDependencies().add(JavaPlugin.RUNTIME_CONFIGURATION_NAME, "com.google.gwt:gwt-servlet:"+gwtVersion);
						}});
				}
			}});
		
	}

}
