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

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.ExecResult;
import org.gradle.process.JavaExecSpec;

import de.richsource.gradle.plugins.gwt.internal.ActionClosure;

public abstract class AbstractGwtActionTask extends DefaultTask {

	private List<String> modules;

	private FileCollection src;
	
	private FileCollection classpath;
	
	private String minHeapSize;
	
	private String maxHeapSize;
	
	private final String main;
	
	private List<Object> args = new ArrayList<Object>();
	
	private List<Object> jvmArgs = new ArrayList<Object>();
	
	private boolean debug;
	
	public AbstractGwtActionTask(String main) {
		this.main = main;
	}
	
	@TaskAction
	public void exec() {
		 final ExecResult execResult = getProject().javaexec(new ActionClosure<JavaExecSpec>(this, new Action<JavaExecSpec>() {
			@Override
			public void execute(JavaExecSpec javaExecSpec) {
				if (getSrc() == null) {
					throw new InvalidUserDataException("No Source is set");
				}
				if (getClasspath() == null) {
					throw new InvalidUserDataException("Classpath is not set");
				}
				if (getModules() == null || getModules().isEmpty()) {
					throw new InvalidUserDataException("No module[s] given");
				}
				
				javaExecSpec.setMain(main);
				javaExecSpec.setDebug(isDebug());
				
				// "Fixes" convention mapping
				javaExecSpec.setMinHeapSize(getMinHeapSize());
				javaExecSpec.setMaxHeapSize(getMaxHeapSize());
				
				FileCollection classpath = getClasspath();
				if (prependSrcToClasspath()) {
					classpath = getSrc().plus(classpath);
				}
				javaExecSpec.setClasspath(classpath);
				
				addArgs();
				javaExecSpec.jvmArgs(jvmArgs);
				javaExecSpec.args(args);
				// the module names are expected to be the last parameters
				javaExecSpec.args(getModules());
			}
		}));
		execResult.assertNormalExitValue().rethrowFailure();
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
	
	protected void args(Object... args) {
		this.args.addAll(Arrays.asList(args));
	}
	
	protected void jvmArgs(Object... args) {
		this.jvmArgs.addAll(Arrays.asList(args));
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

	@Input
	public FileCollection getClasspath() {
		return classpath;
	}

	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
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

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
