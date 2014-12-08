/**
 * Copyright (C) 2013-2014 Steffen Schaefer
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Base class for all GWT related tasks.
 */
public abstract class AbstractGwtActionTask extends DefaultTask {

	private List<String> modules;

	private FileCollection src;
	
	private FileCollection classpath;
	
	private String minHeapSize;
	
	private String maxHeapSize;
	
	private final String main;
	
	private List<Object> args = new ArrayList<Object>();

	private String extraJvmArgs;

	private List<Object> jvmArgs = new ArrayList<Object>();

	private boolean debug;
	
	private LogLevel logLevel;
	
	private String sourceLevel;

	private Boolean incremental;
	
	private JsInteropMode jsInteropMode;
	
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

				argIfSet("-XjsInteropMode", getJsInteropMode());
				argOnOff(getIncremental(), "-incremental", "-noincremental");
				argIfSet("-sourceLevel", getSourceLevel());
				argIfSet("-logLevel", getLogLevel());

				addExtraJvmArgs();
				addArgs();

				javaExecSpec.jvmArgs(jvmArgs);
				javaExecSpec.args(args);
				// the module names are expected to be the last parameters
				javaExecSpec.args(getModules());
			}
		}));
		execResult.assertNormalExitValue().rethrowFailure();
	}

	private void addExtraJvmArgs() {
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(getExtraJvmArgs());
		while (m.find())
			this.jvmArgs.add(m.group(1));
	}

	/**
	 * If true this causes that the src is prepended to the classpath. This is set to false for Super Dev Mode as the source is given to it as extra parameter.
	 * 
	 * @return true if src should be prepended to the classpath, false otherwise.
	 */
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
	
	protected void argOnOff(Boolean condition, String onArg, String offArg) {
		if (Boolean.TRUE.equals(condition)) {
			args(onArg);
		} else if (Boolean.FALSE.equals(condition)) {
			args(offArg);
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

	/**
	 * If true the task instance is treated as being a development related task. Development related tasks will have the devModules set by default.
	 * 
	 * @return true if the task is development related, false otherwise.
	 */
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

	/**
	 * Sets the classpath for the spawned java process.
	 * 
	 * @param classpath the classpath to set
	 */
	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
	}

	public String getMinHeapSize() {
		return minHeapSize;
	}

	/**
	 * Sets the minimum heap size for the spawned java process.
	 * 
	 * @param minHeapSize the minimum heap size to set
	 */
	public void setMinHeapSize(String minHeapSize) {
		this.minHeapSize = minHeapSize;
	}

	public String getMaxHeapSize() {
		return maxHeapSize;
	}

	/**
	 * Sets the maximum heap size for the spawned java process.
	 * 
	 * @param maxHeapSize the maximum heap size to set
	 */
	public void setMaxHeapSize(String maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
	}

	public String getExtraJvmArgs() {
		return extraJvmArgs;
	}

	public void setExtraJvmArgs(String value) {
		extraJvmArgs = value;
	}

	public boolean isDebug() {
		return debug;
	}

	/**
	 * If set to true this enables debugging for the spawned java process.
	 * 
	 * @param debug true to enable debugging, false otherwise.
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	/**
	 * Sets the {@link LogLevel} for this task.
	 * 
	 * @param logLevel the log level to set
	 */
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
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
}
