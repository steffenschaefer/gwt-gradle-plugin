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

import java.io.File;
import java.util.concurrent.Callable;

import org.gradle.api.internal.IConventionAware;

/**
 * GWT specific extension for the Test task.
 */
public class GwtTestExtension extends GwtTestOptionsBase {

	private File war;
	private File deploy;
	private File extra;
	private File workDir;
	private File gen;
	private File cacheDir;
	
	protected String getParameterString() {
		final StringBuilder builder = new StringBuilder();
		
		dirArgIfSet(builder, "-war", getWar());
		dirArgIfSet(builder, "-deploy", getDeploy());
		dirArgIfSet(builder, "-extra", getExtra());
		dirArgIfSet(builder, "-workDir", getWorkDir());
		dirArgIfSet(builder, "-gen", getGen());
		
		argIfSet(builder, "-logLevel", getLogLevel());
		
		argIfSet(builder, "-port", Boolean.TRUE.equals(getAutoPort())? "auto" : getPort());
		argIfSet(builder, "-whitelist", getWhitelist());
		argIfSet(builder, "-blacklist", getBlacklist());
		argIfSet(builder, "-logdir", getLogDir());
		argIfSet(builder, "-codeServerPort", Boolean.TRUE.equals(getAutoCodeServerPort())? "auto" : getCodeServerPort());
		
		argIfSet(builder, "-style", getStyle());
		argIfEnabled(builder, getEa(), "-ea");
		argIfEnabled(builder, getDisableClassMetadata(), "-XdisableClassMetadata");
		argIfEnabled(builder, getDisableCastChecking(), "-XdisableCastChecking");
		argIfEnabled(builder, getDraftCompile(), "-draftCompile");
		argIfSet(builder, "-localWorkers", getLocalWorkers());
		argIfEnabled(builder, getProd(), "-prod");
		argIfSet(builder, "-testMethodTimeout", getTestMethodTimeout());
		argIfSet(builder, "-testBeginTimeout", getTestBeginTimeout());
		argIfSet(builder, "-runStyle", getRunStyle());
		argIfEnabled(builder, getNotHeadless(), "-notHeadless");
		argIfEnabled(builder, getStandardsMode(), "-standardsMode");
		argIfEnabled(builder, getQuirksMode(), "-quirksMode");
		argIfSet(builder, "-Xtries", getTries());
		argIfSet(builder, "-userAgents", getUserAgents());
		
		return builder.toString();
	}
	
	private void argIfEnabled(StringBuilder builder, Boolean condition, String arg) {
		if (Boolean.TRUE.equals(condition)) {
			arg(builder, arg);
		}
	}

	private void dirArgIfSet(StringBuilder builder, String arg, File dir) {
		if (dir != null) {
			dir.mkdirs();
			arg(builder, arg, dir);
		}
	}

	private void argIfSet(StringBuilder builder, String arg, Object value) {
		if (value != null) {
			arg(builder, arg, value);
		}
	}
	
	private void arg(StringBuilder builder, Object... args) {
		for(Object arg : args) {
			if(builder.length() > 0) {
				builder.append(' ');
			}
			builder.append(arg.toString());
		}
	}
	
	protected void configure(final GwtPluginExtension extension, final IConventionAware conventionAware) {
		final GwtTestOptions testOptions = extension.getTest();
		
		conventionAware.getConventionMapping().map("war", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return extension.getDevWar();
			}});
		conventionAware.getConventionMapping().map("extra", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return extension.getExtraDir();
			}});
		conventionAware.getConventionMapping().map("workDir", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return extension.getWorkDir();
			}});
		conventionAware.getConventionMapping().map("gen", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return extension.getGenDir();
			}});
		conventionAware.getConventionMapping().map("cacheDir", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return extension.getCacheDir();
			}});
		conventionAware.getConventionMapping().map("logLevel", new Callable<LogLevel>(){
			@Override
			public LogLevel call() throws Exception {
				return extension.getLogLevel();
			}});
		
		conventionAware.getConventionMapping().map("port", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getPort();
			}});
		conventionAware.getConventionMapping().map("autoPort", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getAutoPort();
			}});
		conventionAware.getConventionMapping().map("whitelist", new Callable<String>(){
			@Override
			public String call() throws Exception {
				return testOptions.getWhitelist();
			}});
		conventionAware.getConventionMapping().map("blacklist", new Callable<String>(){
			@Override
			public String call() throws Exception {
				return testOptions.getBlacklist();
			}});
		conventionAware.getConventionMapping().map("logDir", new Callable<File>(){
			@Override
			public File call() throws Exception {
				return testOptions.getLogDir();
			}});
		conventionAware.getConventionMapping().map("codeServerPort", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getCodeServerPort();
			}});
		conventionAware.getConventionMapping().map("autoCodeServerPort", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getAutoCodeServerPort();
			}});
		
		conventionAware.getConventionMapping().map("style", new Callable<Style>(){
			@Override
			public Style call() throws Exception {
				return testOptions.getStyle();
			}});
		conventionAware.getConventionMapping().map("ea", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getEa();
			}});
		conventionAware.getConventionMapping().map("disableClassMetadata", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getDisableClassMetadata();
			}});
		conventionAware.getConventionMapping().map("disableCastChecking", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getDisableCastChecking();
			}});
		conventionAware.getConventionMapping().map("draftCompile", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getDraftCompile();
			}});
		conventionAware.getConventionMapping().map("localWorkers", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getLocalWorkers();
			}});
		conventionAware.getConventionMapping().map("prod", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getProd();
			}});
		conventionAware.getConventionMapping().map("testMethodTimeout", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getTestMethodTimeout();
			}});
		conventionAware.getConventionMapping().map("testBeginTimeout", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getTestBeginTimeout();
			}});
		conventionAware.getConventionMapping().map("runStyle", new Callable<String>(){
			@Override
			public String call() throws Exception {
				return testOptions.getRunStyle();
			}});
		conventionAware.getConventionMapping().map("notHeadless", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getNotHeadless();
			}});
		conventionAware.getConventionMapping().map("standardsMode", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getStandardsMode();
			}});
		conventionAware.getConventionMapping().map("quirksMode", new Callable<Boolean>(){
			@Override
			public Boolean call() throws Exception {
				return testOptions.getQuirksMode();
			}});
		conventionAware.getConventionMapping().map("tries", new Callable<Integer>(){
			@Override
			public Integer call() throws Exception {
				return testOptions.getTries();
			}});
		conventionAware.getConventionMapping().map("userAgents", new Callable<String>(){
			@Override
			public String call() throws Exception {
				return testOptions.getUserAgents();
			}});
	}

	public File getWar() {
		return war;
	}

	public void setWar(File war) {
		this.war = war;
	}

	public File getDeploy() {
		return deploy;
	}

	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	public File getExtra() {
		return extra;
	}

	public void setExtra(File extra) {
		this.extra = extra;
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	public File getGen() {
		return gen;
	}

	public void setGen(File gen) {
		this.gen = gen;
	}

	public File getCacheDir() {
		return cacheDir;
	}

	public void setCacheDir(File cacheDir) {
		this.cacheDir = cacheDir;
	}
}
