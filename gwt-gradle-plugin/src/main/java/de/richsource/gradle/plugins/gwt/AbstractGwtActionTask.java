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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	private Set<File> src = new HashSet<File>();
	private FileCollection classpath;
	private List<String> extraArgs = new ArrayList<String>();
	
	private String minHeapSize;
	private String maxHeapSize;

	public AbstractGwtActionTask() {
		FileResolver fileResolver = getServices().get(FileResolver.class);
		javaExec = new DefaultJavaExecAction(fileResolver);
	}
	
	@TaskAction
	public void run() {
		if(getSrc() == null || getSrc().isEmpty()) {
			throw new InvalidUserDataException("No Source is set");
		}
		if(getClasspath() == null) {
			throw new InvalidUserDataException("Classpath is not set");
		}
		if(getModules() == null || getModules().isEmpty()) {
			throw new InvalidUserDataException("No module[s] given");
		}
		
		javaExec.setMain(getClassName());
		
		if(prependSrcToClasspath()) {
			javaExec.classpath(getSrc().toArray());
		}
		javaExec.classpath(getClasspath());
		
		javaExec.setMinHeapSize(getMinHeapSize());
		javaExec.setMaxHeapSize(getMaxHeapSize());
		
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

	public void setModules(List<String> modules) {
		this.modules = modules;
	}
	
	public void javaArgs(Object... args) {
		javaExec.jvmArgs(args);
	}
	
	public List<String> getExtraArgs() {
		return extraArgs;
	}
	
	protected void arg(Object... arg) {
		javaExec.args(arg);
	}
	
	protected void argIfEnabled(Boolean condition, String arg) {
		if(Boolean.TRUE.equals(condition)) {
			arg(arg);
		}
	}
	
	protected void dirArgIfSet(String arg, File dir) {
		if(dir != null) {
			dir.mkdirs();
			arg(arg, dir);
		}
	}
	
	protected void argIfSet(String arg, Object value) {
		if(value != null) {
			arg(arg, value);
		}
	}
	
	public void extraArg(String arg) {
		extraArgs.add(arg);
	}
	
	protected abstract String getClassName();
	
	protected abstract void addArgs();
	
	protected boolean isDevTask() {
		return true;
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
	
	@InputFiles
	public Set<File> getSrc() {
		return src;
	}

	public void setSrc(Set<File> src) {
		this.src = src;
	}
	
	public void src(File src) {
		this.src.add(src);
	}
	
	public void src(Collection<File> src) {
		this.src.addAll(src);
	}

	@InputFiles
	public FileCollection getClasspath() {
		return classpath;
	}

	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
	}
}
