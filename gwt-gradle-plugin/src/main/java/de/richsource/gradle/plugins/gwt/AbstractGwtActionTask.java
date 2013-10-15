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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gradle.api.InvalidUserDataException;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.internal.DefaultJavaExecAction;
import org.gradle.process.internal.JavaExecAction;

public abstract class AbstractGwtActionTask extends ConventionTask {

	private JavaExecAction javaExec;

	private List<String> modules;

	private FileCollection src;
	private FileCollection classpath;
	private List<String> extraArgs = new ArrayList<String>();

	private String minHeapSize;
	private String maxHeapSize;
	
	private boolean debug = false;

	public AbstractGwtActionTask() {
		FileResolver fileResolver = getServices().get(FileResolver.class);
		javaExec = new DefaultJavaExecAction(fileResolver);
	}

	@TaskAction
	public void run() {
		if (getSrc() == null) {
			throw new InvalidUserDataException("No Source is set");
		}
		if (getClasspath() == null) {
			throw new InvalidUserDataException("Classpath is not set");
		}
		if (getModules() == null || getModules().isEmpty()) {
			throw new InvalidUserDataException("No module[s] given");
		}

		javaExec.setMain(getClassName());

		if (prependSrcToClasspath()) {
			javaExec.classpath(getSrc());
		}
		javaExec.classpath(getClasspath());

		javaExec.setMinHeapSize(getMinHeapSize());
		javaExec.setMaxHeapSize(getMaxHeapSize());
		javaExec.setDebug(isDebug());

		addArgs();
		javaExec.args(getExtraArgs());
		// the module names are expected to be the last parameters
		javaExec.args(getModules());

		javaExec.execute();
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

	/**
	 * Adds additional arguments for the Java VM spawned by this task.
	 * 
	 * @param args
	 *            java args to be added
	 */
	public void javaArgs(Object... args) {
		javaExec.jvmArgs(args);
	}

	public List<String> getExtraArgs() {
		return extraArgs;
	}

	/**
	 * Adds arguments that are given to the task.
	 * 
	 * @param arg
	 *            arguments to be added
	 */
	protected void arg(Object... arg) {
		javaExec.args(arg);
	}

	protected void argIfEnabled(Boolean condition, String arg) {
		if (Boolean.TRUE.equals(condition)) {
			arg(arg);
		}
	}

	protected void dirArgIfSet(String arg, File dir) {
		if (dir != null) {
			dir.mkdirs();
			arg(arg, dir);
		}
	}

	protected void argIfSet(String arg, Object value) {
		if (value != null) {
			arg(arg, value);
		}
	}

	/**
	 * Adds additional program arguments for the process spawned by this task.
	 * 
	 * @param args
	 *            additional arguments to add
	 */
	public void extraArg(String... args) {
		extraArgs.addAll(Arrays.asList(args));
	}

	/**
	 * Defines the main class to be called when this task is executed.
	 * 
	 * @return the fully qualified name of the main class to be executed. Must
	 *         not be null.
	 */
	protected abstract String getClassName();

	/**
	 * Called directly before executing this task. Subclasses are expected to
	 * add all args/javaArgs needed for the execution.
	 */
	protected abstract void addArgs();

	protected boolean isDevTask() {
		return true;
	}

	public String getMinHeapSize() {
		return minHeapSize;
	}

	/**
	 * Sets the minimum heap size (-Xms java arg) to be used by the java process
	 * spawned by this task.
	 * 
	 * @param minHeapSize
	 *            the minimum heap size to set. Examples: 128M, 2G
	 */
	public void setMinHeapSize(String minHeapSize) {
		this.minHeapSize = minHeapSize;
	}

	public String getMaxHeapSize() {
		return maxHeapSize;
	}

	/**
	 * Sets the maximum heap size (-Xmx java arg) to be used by the java process
	 * spawned by this task.
	 * 
	 * @param maxHeapSize
	 *            the maximum heap size to set. Examples: 128M, 2G
	 */
	public void setMaxHeapSize(String maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
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

	@InputFiles
	public FileCollection getClasspath() {
		return classpath;
	}

	/**
	 * Sets the classpath for the java process spawned by this task. This
	 * classpath must contain all libraries used by GWT (especially gwt-dev and
	 * gwt-user libraries).
	 * 
	 * @param classpath
	 *            the classpat to set
	 */
	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
