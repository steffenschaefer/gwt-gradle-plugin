package de.richsource.gradle.plugins.gwt;

import java.io.File;

public interface GwtSuperDevOptions {

	/**
	 * @see #setWorkDir(File)
	 * 
	 * @return the workDir
	 */
	File getWorkDir();

	/**
	 * Sets the "-workDir" option that specifies the directory where the Super Dev Mode outputs its
	 * generated files.
	 * 
	 * @param workDir
	 *            the workDir to set
	 */
	void setWorkDir(File workDir);

	String getBindAddress();

	/**
	 * Sets the "-bindAddress" option that defines to which network ip the socket should be bound.
	 * This is relevant if the Super Dev Mode should be reachable from a remote
	 * host.
	 * 
	 * @param bindAddress
	 *            the bindAddress to set
	 */
	void setBindAddress(String bindAddress);

	Integer getPort();

	/**
	 * Sets the "-port" option that defines to which port the socket should be bound.
	 * 
	 * @param port
	 *            the port to set. Valid range is [1; 65535].
	 */
	void setPort(Integer port);

	Boolean getNoPrecompile();

	/**
	 * Sets the "-noprecompile" flag that causes the Super Dev Mode to not compile the
	 * modules on startup but only at access.
	 * 
	 * @param noPrecompile true if the noPrecompile flag should be set, false otherwise
	 */
	void setNoPrecompile(Boolean noPrecompile);

}