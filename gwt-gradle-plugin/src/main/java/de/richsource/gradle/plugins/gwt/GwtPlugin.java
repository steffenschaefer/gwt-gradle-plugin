package de.richsource.gradle.plugins.gwt;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GwtPlugin implements Plugin<Project> {
	public static final String EXTENSION_NAME = "gwt";

	@Override
	public void apply(Project project) {
		project.getPlugins().apply(GwtBasePlugin.class);
		GwtPluginExtension gwtPluginExtension = project.getExtensions().getByType(GwtPluginExtension.class);
	}

}
