package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GwtPluginExtension {
	private String gwtVersion;
	private File devWar;
	private File extraDir;
	private File workDir;
	private File genDir;
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

	public File getDevWar() {
		return devWar;
	}

	public void setDevWar(File devWar) {
		this.devWar = devWar;
	}

	public File getExtraDir() {
		return extraDir;
	}

	public void setExtraDir(File extraDir) {
		this.extraDir = extraDir;
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	public File getGenDir() {
		return genDir;
	}

	public void setGenDir(File genDir) {
		this.genDir = genDir;
	}
}
