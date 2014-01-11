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
import java.util.concurrent.Callable;

import org.gradle.api.Task;
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.specs.Spec;

import de.richsource.gradle.plugins.gwt.internal.GwtSuperDevOptionsImpl;

/**
 * Task to run the GWT Super Dev Mode.
 */
public class GwtSuperDev extends AbstractGwtActionTask implements GwtSuperDevOptions {
	
	private final GwtSuperDevOptions options = new GwtSuperDevOptionsImpl();
	
	public GwtSuperDev() {
		super("com.google.gwt.dev.codeserver.CodeServer");
		
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}

	@Override
	protected void addArgs() {
		for (File srcDir : getSrc()) {
			// TODO warning if file?
			if(srcDir.exists() && srcDir.isDirectory()) {
				argIfSet("-src", srcDir);
			}
		}
		dirArgIfSet("-workDir", getWorkDir());
		argIfSet("-bindAddress", getBindAddress());
		argIfSet("-port", getPort());
		argIfEnabled(getNoPrecompile(), "-noprecompile");
	}
	
	protected void configure(final GwtSuperDevOptions options) {
		ConventionMapping conventionMapping =((IConventionAware)this).getConventionMapping();
		conventionMapping.map("bindAddress", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBindAddress();
			}
		});
		conventionMapping.map("port", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getPort();
			}
		});
		conventionMapping.map("noPrecompile", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getNoPrecompile();
			}
		});
	}
	
	@Override
	protected boolean prependSrcToClasspath() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public File getWorkDir() {
		return options.getWorkDir();
	}

	/** {@inheritDoc} */
	@Override
	public void setWorkDir(File workDir) {
		options.setWorkDir(workDir);
	}

	/** {@inheritDoc} */
	@Override
	public String getBindAddress() {
		return options.getBindAddress();
	}

	/** {@inheritDoc} */
	@Override
	public void setBindAddress(String bindAddress) {
		options.setBindAddress(bindAddress);
	}

	/** {@inheritDoc} */
	@Override
	public Integer getPort() {
		return options.getPort();
	}

	/** {@inheritDoc} */
	@Override
	public void setPort(Integer port) {
		options.setPort(port);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getNoPrecompile() {
		return options.getNoPrecompile();
	}

	/** {@inheritDoc} */
	@Override
	public void setNoPrecompile(Boolean noPrecompile) {
		options.setNoPrecompile(noPrecompile);
	}
}
