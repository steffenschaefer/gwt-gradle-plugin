package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

public class GwtBasePlugin implements Plugin<Project> {
	public static final String EXTENSION_NAME = "gwt";
	public static final String BUILD_DIR = "gwt";
	public static final String OUT_DIR = "out";
	public static final String EXTRA_DIR = "extra";
	public static final String WORK_DIR = "work";

	@Override
	public void apply(Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		final GwtPluginExtension extension = project.getExtensions().create(EXTENSION_NAME, GwtPluginExtension.class);
		
		GwtCompileTask compileTask = project.getTasks().create(GwtCompileTask.NAME, GwtCompileTask.class);
		final File buildDir = new File(project.getBuildDir(), BUILD_DIR);
		final File outDir = new File(buildDir, OUT_DIR);
		final File extraDir = new File(buildDir, EXTRA_DIR);
		final File workDir = new File(buildDir, WORK_DIR);
		compileTask.setWar(outDir);
		compileTask.setExtra(extraDir);
		compileTask.setWorkDir(workDir);
		
		JavaPluginConvention javaConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
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
	}

}
