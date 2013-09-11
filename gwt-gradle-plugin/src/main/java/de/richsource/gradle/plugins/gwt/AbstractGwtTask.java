/**
 * Copyright (C) 2013 Steffen Schaefer
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

public abstract class AbstractGwtTask extends AbstractGwtActionTask {
	private File war;
	private File deploy;
	private File extra;
	private File workDir;
	private File gen;
	private File cacheDir;
	private LogLevel logLevel;
	
	@Override
	protected void addArgs() {
		dirArgIfSet("-war", getWar());
		dirArgIfSet("-deploy", getDeploy());
		dirArgIfSet("-extra", getExtra());
		dirArgIfSet("-workDir", getWorkDir());
		dirArgIfSet("-gen", getGen());
		
		argIfSet("-logLevel", getLogLevel());
		
		final File cacheDir = getCacheDir();
		if(cacheDir != null) {
			cacheDir.mkdirs();
			javaArgs("-Dgwt.persistentunitcachedir="+cacheDir.toString());
		}
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

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}
}
