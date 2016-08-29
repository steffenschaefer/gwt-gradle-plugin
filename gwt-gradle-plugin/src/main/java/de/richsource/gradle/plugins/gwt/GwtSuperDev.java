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
		if(!Boolean.TRUE.equals(getUseClasspathForSrc())) {
			for (File srcDir : getSrc()) {
				// TODO warning if file?
				if(srcDir.exists() && srcDir.isDirectory()) {
					argIfSet("-src", srcDir);
				}
			}
		}
		dirArgIfSet("-workDir", getWorkDir());
		argIfSet("-bindAddress", getBindAddress());
		argIfSet("-port", getPort());
		argIfEnabled(getNoPrecompile(), "-noprecompile");
		argOnOff(getAllowMissingSrc(), "-allowMissingSrc", "-noallowMissingSrc");
		argOnOff(getFailOnError(), "-failOnError", "-nofailOnError");
		argOnOff(getCompileTest(), "-compileTest", "-nocompileTest");
		argIfSet("-compileTestRecompiles", getCompileTestRecompiles());
		argIfSet("-launcherDir", getLauncherDir());
		argOnOff(getClosureFormattedOutput(), "-XclosureFormattedOutput", "-XnoclosureFormattedOutput");
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
		conventionMapping.map("useClasspathForSrc", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getUseClasspathForSrc();
			}
		});
		conventionMapping.map("allowMissingSrc", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getAllowMissingSrc();
			}
		});
		conventionMapping.map("failOnError", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getFailOnError();
			}
		});
		conventionMapping.map("compileTest", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getCompileTest();
			}
		});
		conventionMapping.map("compileTestRecompiles", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getCompileTestRecompiles();
			}
		});
		conventionMapping.map("launcherDir", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return options.getLauncherDir();
			}
		});
		conventionMapping.map("closureFormattedOutput", options::getClosureFormattedOutput);
	}
	
	@Override
	protected boolean prependSrcToClasspath() {
		return Boolean.TRUE.equals(getUseClasspathForSrc());
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

	/** {@inheritDoc} */
	@Override
	public void setUseClasspathForSrc(Boolean useClasspathForSrc) {
		options.setUseClasspathForSrc(useClasspathForSrc);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getUseClasspathForSrc() {
		return options.getUseClasspathForSrc();
	}

	/** {@inheritDoc} */
	@Override
	public void setLauncherDir(File launcherDir) {
		options.setLauncherDir(launcherDir);
	}

	/** {@inheritDoc} */
	@Override
	public File getLauncherDir() {
		return options.getLauncherDir();
	}

	/** {@inheritDoc} */
	@Override
	public void setCompileTestRecompiles(Integer compileTestRecompiles) {
		options.setCompileTestRecompiles(compileTestRecompiles);
	}

	/** {@inheritDoc} */
	@Override
	public Integer getCompileTestRecompiles() {
		return options.getCompileTestRecompiles();
	}

	/** {@inheritDoc} */
	@Override
	public void setCompileTest(Boolean compileTest) {
		options.setCompileTest(compileTest);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getCompileTest() {
		return options.getCompileTest();
	}

	/** {@inheritDoc} */
	@Override
	public void setFailOnError(Boolean failOnError) {
		options.setFailOnError(failOnError);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getFailOnError() {
		return options.getFailOnError();
	}

	/** {@inheritDoc} */
	@Override
	public void setAllowMissingSrc(Boolean allowMissingSrc) {
		options.setAllowMissingSrc(allowMissingSrc);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAllowMissingSrc() {
		return options.getAllowMissingSrc();
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getClosureFormattedOutput() {
		return options.getClosureFormattedOutput();
	}

	/** {@inheritDoc} */
	@Override
	public void setClosureFormattedOutput(Boolean closureFormattedOutput) {
		options.setClosureFormattedOutput(closureFormattedOutput);
	}
}
