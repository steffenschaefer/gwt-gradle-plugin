package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GwtPluginExtension {
	private String gwtVersion;
	private File devWar;
	private List<String> modules = new ArrayList<String>();
	private List<String> devModules = new ArrayList<String>();

	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	public String getGwtVersion() {
		return gwtVersion;
	}

	public void setGwtVersion(String gwtVersion) {
		this.gwtVersion = gwtVersion;
	}

	public List<String> getDevModules() {
		return devModules;
	}

	public void setDevModules(List<String> devModules) {
		this.devModules = devModules;
	}
}
