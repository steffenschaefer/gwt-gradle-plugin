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
import java.util.List;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.JavaExec;

public abstract class AbstractGwtActionTask extends JavaExec {

	private List<String> modules;

	private FileCollection src;
	
	@Override
	public void exec() {
		if (getSrc() == null) {
			throw new InvalidUserDataException("No Source is set");
		}
		if (getClasspath() == null) {
			throw new InvalidUserDataException("Classpath is not set");
		}
		if (getModules() == null || getModules().isEmpty()) {
			throw new InvalidUserDataException("No module[s] given");
		}
		
		// "Fixes" convention mapping
		setMinHeapSize(getMinHeapSize());
		setMaxHeapSize(getMaxHeapSize());

		FileCollection classpath = getClasspath();
		if (prependSrcToClasspath()) {
			classpath = getSrc().plus(classpath);
		}
		setClasspath(classpath);

		addArgs();
		// the module names are expected to be the last parameters
		args(getModules());

		super.exec();
	}

	protected boolean prependSrcToClasspath() {
		return true;
	}

	@Input
	public List<String> getModules() {
		return modules;
	}

	/**
	 * Sets the GWT modules (fully qualified names) to be used by this task.
	 * 
	 * @param modules
	 *            GWT modules to be set for this task
	 */
	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	protected void argIfEnabled(Boolean condition, String arg) {
		if (Boolean.TRUE.equals(condition)) {
			args(arg);
		}
	}

	protected void dirArgIfSet(String arg, File dir) {
		if (dir != null) {
			dir.mkdirs();
			args(arg, dir);
		}
	}

	protected void argIfSet(String arg, Object value) {
		if (value != null) {
			args(arg, value);
		}
	}

	/**
	 * Called directly before executing this task. Subclasses are expected to
	 * add all args/javaArgs needed for the execution.
	 */
	protected abstract void addArgs();

	protected boolean isDevTask() {
		return true;
	}

	@InputFiles
	public FileCollection getSrc() {
		return src;
	}

	/**
	 * Sets the source directories used by this task instance. These source
	 * directories are used by GWT to read java source files from.
	 * 
	 * @param src
	 *            source directories to set
	 */
	public void setSrc(FileCollection src) {
		this.src = src;
	}
}
