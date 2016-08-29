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

import groovy.lang.Closure;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gradle.api.file.FileCollection;
import org.gradle.util.ConfigureUtil;

import de.richsource.gradle.plugins.gwt.internal.GwtCompileOptionsImpl;
import de.richsource.gradle.plugins.gwt.internal.GwtDevOptionsImpl;
import de.richsource.gradle.plugins.gwt.internal.GwtSuperDevOptionsImpl;

public class GwtPluginExtension {
	private String gwtVersion;
	private boolean codeserver = true;
	private boolean elemental = false;
	private File devWar;
	private File extraDir;
	private File workDir;
	private File genDir;
	private File cacheDir;
	private LogLevel logLevel;
	private List<String> modules = new ArrayList<String>();
	private List<String> devModules = new ArrayList<String>();
	private FileCollection src;
	private String sourceLevel;
	private String modulePathPrefix;

	private Boolean incremental;
	private JsInteropMode jsInteropMode;
	private Boolean generateJsInteropExports;
	private MethodNameDisplayMode methodNameDisplayMode;
	
	private String minHeapSize = "256M";
	private String maxHeapSize = "256M";
	
	private final GwtDevOptions dev = new GwtDevOptionsImpl();
	private final GwtSuperDevOptions superDev = new GwtSuperDevOptionsImpl();
	private final GwtCompileOptions compiler = new GwtCompileOptionsImpl();
	private final GwtTestOptions test = new GwtTestOptions();

	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules.clear();
		this.modules.addAll(modules);
	}
	
	public void modules(String... modules) {
		this.modules.addAll(Arrays.asList(modules));
	}

	public String getGwtVersion() {
		return gwtVersion;
	}

	public void setGwtVersion(String gwtVersion) {
		this.gwtVersion = gwtVersion;
	}
	
	public boolean isCodeserver() {
		return codeserver;
	}
	
	public void setCodeserver(boolean codeserver) {
		this.codeserver = codeserver;
	}
	
	public boolean isElemental() {
		return elemental;
	}
	
	public void setElemental(boolean elemental) {
		this.elemental = elemental;
	}

	public List<String> getDevModules() {
		return devModules;
	}

	public void setDevModules(List<String> devModules) {
		this.devModules.clear();
		this.devModules.addAll(devModules);
	}
	
	public void devModules(String... modules) {
		this.devModules.addAll(Arrays.asList(modules));
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
	
	public GwtTestOptions getTest() {
		return test;
	}

	public GwtPluginExtension test(Closure<GwtTestOptions> c) {
		ConfigureUtil.configure(c, test);
		return this;
	}

	public FileCollection getSrc() {
		return src;
	}

	public void setSrc(FileCollection src) {
		this.src = src;
	}
	
	public String getSourceLevel() {
		return sourceLevel;
	}
	
	public void setSourceLevel(String sourceLevel) {
		this.sourceLevel = sourceLevel;
	}
	
	public Boolean getIncremental() {
		return incremental;
	}
	
	public void setIncremental(Boolean incremental) {
		this.incremental = incremental;
	}
	
	public JsInteropMode getJsInteropMode() {
		return jsInteropMode;
	}
	
	public void setJsInteropMode(JsInteropMode jsInteropMode) {
		this.jsInteropMode = jsInteropMode;
	}

	public String getModulePathPrefix() {
		return modulePathPrefix;
	}

	public void setModulePathPrefix(String modulePathPrefix) {
		this.modulePathPrefix = modulePathPrefix;
	}

	public Boolean getGenerateJsInteropExports() {
		return generateJsInteropExports;
	}

	/**
	 * If set to true, this adds the parameter -generateJsInteropExports.
	 * If set to false, this adds the parameter -nogenerateJsInteropExports.
	 * Added in GWT 2.8.
	 */
	public void setGenerateJsInteropExports(Boolean generateJsInteropExports) {
		this.generateJsInteropExports = generateJsInteropExports;
	}
	
	public MethodNameDisplayMode getMethodNameDisplayMode() {
		return methodNameDisplayMode;
	}
	
	/**
	 * If set, this causes the "-XmethodNameDisplayMode" (added in GWT 2.7/2.8) parameter to be added.
	 * 
	 * @param methodNameDisplayMode
	 */
	public void setMethodNameDisplayMode(MethodNameDisplayMode methodNameDisplayMode) {
		this.methodNameDisplayMode = methodNameDisplayMode;
	}
}
