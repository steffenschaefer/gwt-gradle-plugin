package de.richsource.gradle.plugins.gwt;

import java.io.File;

public class GwtSuperDevOptions {
	private File workDir;

	private String bindAddress;
	private Integer port;
	private Boolean noPrecompile;

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getNoPrecompile() {
		return noPrecompile;
	}

	public void setNoPrecompile(Boolean noPrecompile) {
		this.noPrecompile = noPrecompile;
	}
}
