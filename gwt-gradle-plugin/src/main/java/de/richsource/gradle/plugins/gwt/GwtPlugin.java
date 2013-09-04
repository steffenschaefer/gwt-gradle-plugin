package de.richsource.gradle.plugins.gwt;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.bundling.War;

public class GwtPlugin implements Plugin<Project> {
	public static final String EXTENSION_NAME = "gwt";

	@Override
	public void apply(Project project) {
		project.getPlugins().apply(GwtBasePlugin.class);
		project.getPlugins().apply(WarPlugin.class);
		
		GwtPluginExtension extension = project.getExtensions().getByType(GwtPluginExtension.class);
		GwtCompileTask compileTask = (GwtCompileTask) project.getTasks().getByName(GwtCompileTask.NAME);
		War warTask = (War) project.getTasks().getByName(WarPlugin.WAR_TASK_NAME);

		warTask.dependsOn(compileTask);
		warTask.from(compileTask.getWar());
	}

}
