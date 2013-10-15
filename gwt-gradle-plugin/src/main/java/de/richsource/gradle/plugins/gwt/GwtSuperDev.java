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
import org.gradle.api.specs.Spec;

import de.richsource.gradle.plugins.gwt.internal.GwtSuperDevOptionsImpl;

//CodeServer [-bindAddress address] [-port port] [-workDir dir] [-src
// dir] [module]
//
// where
// -bindAddress The ip address of the code server. Defaults to 127.0.0.1.
// -port The port where the code server will run.
// -workDir The root of the directory tree where the code server will write compiler output. If not supplied, a temporary directory will be used.
// -src A directory containing GWT source to be prepended to the classpath for compiling.
// and
// module The GWT modules that the code server should compile. (Example: com.example.MyApp)
public class GwtSuperDev extends AbstractGwtActionTask implements GwtSuperDevOptions {
	
	private final GwtSuperDevOptions options = new GwtSuperDevOptionsImpl();
	
	public GwtSuperDev() {
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.codeserver.CodeServer";
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
		conventionMapping("bindAddress", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBindAddress();
			}
		});
		conventionMapping("port", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getPort();
			}
		});
		conventionMapping("noPrecompile", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getNoPrecompile();
			}
		});
	}
	
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
