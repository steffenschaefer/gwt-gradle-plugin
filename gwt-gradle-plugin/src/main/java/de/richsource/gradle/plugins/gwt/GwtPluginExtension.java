package de.richsource.gradle.plugins.gwt;

import java.util.ArrayList;
import java.util.List;

public class GwtPluginExtension {
	private List<String> modules = new ArrayList<String>();

	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}
}
