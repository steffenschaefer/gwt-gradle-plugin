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
package de.richsource.gradle.plugins.gwt.internal;

import java.io.File;

import de.richsource.gradle.plugins.gwt.GwtSuperDevOptions;

/**
 * Default implementation of {@link GwtSuperDevOptions}.
 */
public class GwtSuperDevOptionsImpl implements GwtSuperDevOptions {
	private File workDir;

	private String bindAddress;
	private Integer port;
	private Boolean noPrecompile;
	private Boolean useClasspathForSrc;
	private Boolean allowMissingSrc;
	private Boolean failOnError;
	private Boolean compileTest;
	private Integer compileTestRecompiles;
	private File launcherDir;
	// -X[no]closureFormattedOutput
	private Boolean closureFormattedOutput;

	/** {@inheritDoc} */
	@Override
	public File getWorkDir() {
		return workDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	/** {@inheritDoc} */
	@Override
	public String getBindAddress() {
		return bindAddress;
	}

	/** {@inheritDoc} */
	@Override
	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getPort() {
		return port;
	}

	/** {@inheritDoc} */
	@Override
	public void setPort(Integer port) {
		this.port = port;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getNoPrecompile() {
		return noPrecompile;
	}

	/** {@inheritDoc} */
	@Override
	public void setNoPrecompile(Boolean noPrecompile) {
		this.noPrecompile = noPrecompile;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUseClasspathForSrc(Boolean useClasspathForSrc) {
		this.useClasspathForSrc = useClasspathForSrc;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getUseClasspathForSrc() {
		return useClasspathForSrc;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAllowMissingSrc() {
		return allowMissingSrc;
	}

	/** {@inheritDoc} */
	@Override
	public void setAllowMissingSrc(Boolean allowMissingSrc) {
		this.allowMissingSrc = allowMissingSrc;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getFailOnError() {
		return failOnError;
	}

	/** {@inheritDoc} */
	@Override
	public void setFailOnError(Boolean failOnError) {
		this.failOnError = failOnError;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getCompileTest() {
		return compileTest;
	}

	/** {@inheritDoc} */
	@Override
	public void setCompileTest(Boolean compileTest) {
		this.compileTest = compileTest;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getCompileTestRecompiles() {
		return compileTestRecompiles;
	}

	/** {@inheritDoc} */
	@Override
	public void setCompileTestRecompiles(Integer compileTestRecompiles) {
		this.compileTestRecompiles = compileTestRecompiles;
	}

	/** {@inheritDoc} */
	@Override
	public File getLauncherDir() {
		return launcherDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setLauncherDir(File launcherDir) {
		this.launcherDir = launcherDir;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getClosureFormattedOutput() {
		return closureFormattedOutput;
	}

	/** {@inheritDoc} */
	@Override
	public void setClosureFormattedOutput(Boolean closureFormattedOutput) {
		this.closureFormattedOutput = closureFormattedOutput;
	}
}
