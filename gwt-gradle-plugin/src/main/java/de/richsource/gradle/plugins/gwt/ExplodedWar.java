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

import groovy.lang.Closure;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

import de.richsource.gradle.plugins.gwt.internal.ActionClosure;

public class ExplodedWar extends DefaultTask {
	
	private final CopySpec root;
	
	private File destinationDir;
	private File webXml;

    private FileCollection classpath;
    private final CopySpec webInf;
	
	public ExplodedWar() {
		root = getProject().copySpec(new ActionClosure<CopySpec>(this, new Action<CopySpec>() {
			@Override
			public void execute(CopySpec spec) {
			}
		}));
		
		webInf = root.into("WEB-INF", new ActionClosure<CopySpec>(this, new Action<CopySpec>(){
			@Override
			public void execute(CopySpec spec) {
			}}));
		
		 webInf.into("", (new ActionClosure<CopySpec>(this, new Action<CopySpec>(){
				@Override
				public void execute(CopySpec spec) {
					spec.from(new Callable<File>() {
						
						@Override
						public File call() throws Exception {
							return getWebXml();
						}
					}).rename(".*", "web.xml");
				}})));
		
        webInf.into("classes", new ActionClosure<CopySpec>(this, new Action<CopySpec>(){
			@Override
			public void execute(CopySpec spec) {
				spec.from(new Callable<Iterable<File>>() {
					
					@Override
					public Iterable<File> call() throws Exception {
						final FileCollection classpath = getClasspath();
						return	classpath == null ? Collections.<File>emptyList() : classpath.filter(new Spec<File>() {
							@Override
							public boolean isSatisfiedBy(File file) {
								return file.isDirectory();
							}
						});
					}
				});
			}}));
        
        webInf.into("lib", new ActionClosure<CopySpec>(this, new Action<CopySpec>(){
        	@Override
        	public void execute(CopySpec spec) {
        		spec.from(new Callable<Iterable<File>>() {
        			
        			@Override
        			public Iterable<File> call() throws Exception {
        				final FileCollection classpath = getClasspath();
        				return	classpath == null ? Collections.<File>emptyList() : classpath.filter(new Spec<File>() {
        					@Override
        					public boolean isSatisfiedBy(File file) {
        						return file.isFile();
        					}
        				});
        			}
        		});
        	}}));
	}
	
	@TaskAction
	protected void buildWarTemplate() {
		getProject().copy(new ActionClosure<CopySpec>(this, new Action<CopySpec>() {

			@Override
			public void execute(CopySpec spec) {
				spec.into(getDestinationDir());
				spec.with(root);
			}}));
	}
	
	public CopySpec getWebInf() {
        return webInf.into("", new ActionClosure<CopySpec>(this, new Action<CopySpec>(){
			@Override
			public void execute(CopySpec arg0) {
			}}));
    }
	
	public CopySpec webInf(Closure<CopySpec> configureClosure) {
        return webInf.into("", configureClosure);
    }

	@InputFiles
	@Optional
	public FileCollection getClasspath() {
		return classpath;
	}

	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
	}
	
	public void classpath(Object... classpath) {
        FileCollection oldClasspath = getClasspath();
        this.classpath = oldClasspath == null ? getProject().files(classpath) : getProject().files( this.classpath, classpath);
    }

	@InputFile
	@Optional
	public File getWebXml() {
		return webXml;
	}

	public void setWebXml(File webXml) {
		this.webXml = webXml;
	}

	public File getDestinationDir() {
		return destinationDir;
	}

	public void setDestinationDir(File destinationDir) {
		this.destinationDir = destinationDir;
	}
	
	public CopySpec from(Object... input) {
		return root.from(input);
	}
}
