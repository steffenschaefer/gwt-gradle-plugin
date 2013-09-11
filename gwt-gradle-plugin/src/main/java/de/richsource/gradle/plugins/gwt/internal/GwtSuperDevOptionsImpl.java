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
}
