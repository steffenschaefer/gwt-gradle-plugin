package de.richsource.gradle.plugins.gwt;

import groovy.lang.Closure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.gradle.util.ConfigureUtil;

import de.richsource.gradle.plugins.gwt.internal.GwtCompileOptionsImpl;
import de.richsource.gradle.plugins.gwt.internal.GwtDevOptionsImpl;
import de.richsource.gradle.plugins.gwt.internal.GwtSuperDevOptionsImpl;

public class GwtPluginExtension {
	private String gwtVersion;
	private File devWar;
	private File extraDir;
	private File workDir;
	private File genDir;
	private File cacheDir;
	private LogLevel logLevel;
	private List<String> modules = new ArrayList<String>();
	private List<String> devModules = new ArrayList<String>();
	
	private String minHeapSize = "256M";
	private String maxHeapSize = "256M";
	
	private final GwtDevOptions dev = new GwtDevOptionsImpl();
	private final GwtSuperDevOptions superDev = new GwtSuperDevOptionsImpl();
	private final GwtCompileOptions compiler = new GwtCompileOptionsImpl();

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

	public File getCacheDir() {
		return cacheDir;
	}

	public void setCacheDir(File cacheDir) {
		this.cacheDir = cacheDir;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getMinHeapSize() {
		return minHeapSize;
	}

	public void setMinHeapSize(String minHeapSize) {
		this.minHeapSize = minHeapSize;
	}

	public String getMaxHeapSize() {
		return maxHeapSize;
	}

	public void setMaxHeapSize(String maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
	}
	
	public GwtDevOptions getDev() {
		return dev;
	}
	
	public GwtPluginExtension dev(Closure<GwtDevOptions> c) {
		ConfigureUtil.configure(c, dev);
		return this;
	}
	
	public GwtSuperDevOptions getSuperDev() {
		return superDev;
	}
	
	public GwtPluginExtension superDev(Closure<GwtSuperDevOptions> c) {
		ConfigureUtil.configure(c, superDev);
		return this;
	}
	
	public GwtCompileOptions getCompiler() {
		return compiler;
	}
	
	public GwtPluginExtension compiler(Closure<GwtCompileOptions> c) {
		ConfigureUtil.configure(c, compiler);
		return this;
	}
}
