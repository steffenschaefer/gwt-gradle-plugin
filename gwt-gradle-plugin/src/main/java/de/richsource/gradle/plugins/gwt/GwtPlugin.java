package de.richsource.gradle.plugins.gwt;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

public class GwtPlugin implements Plugin<Project> {
	public static final String EXTENSION_NAME = "gwt";

	@Override
	public void apply(Project project) {
		project.getPlugins().apply(JavaPlugin.class);
		GwtPluginExtension gwtPluginExtension = project.getConvention().create(EXTENSION_NAME, GwtPluginExtension.class);
	}

}
