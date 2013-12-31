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

import org.gradle.api.file.CopySpec;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.file.copy.DefaultCopySpec;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Optional;
import org.gradle.util.ConfigureUtil;

public class ExplodedWar extends Copy {
	
	private File webXml;

    private FileCollection classpath;
    private final DefaultCopySpec webInf;
	
	public ExplodedWar() {
		webInf = getRootSpec().addFirst().into("WEB-INF");
        webInf.addChild().into("classes").from(new Callable<Iterable<File>>() {

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
        webInf.addChild().into("lib").from(new Callable<Iterable<File>>() {
        	
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
        webInf.addChild().into("").from(new Callable<File>() {
        	
        	@Override
        	public File call() throws Exception {
        		return getWebXml();
        	}
        }).rename(".*", "web.xml");
	}
	
	public CopySpec getWebInf() {
        return webInf.addChild();
    }
	
	public CopySpec webInf(Closure<CopySpec> configureClosure) {
        return ConfigureUtil.configure(configureClosure, getWebInf());
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
}
