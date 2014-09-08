/**
 * Copyright (C) 2013-2014 Steffen Schaefer
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

/**
 * Defines the options known by the {@link GwtSuperDev} task.
 */
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

	/**
	 * If set to true, this causes the src to be prepended to the classpath instead of using -src parameters.
	 * This is necessary due to a bug in GWT 2.5/2.6.
	 * 
	 * @see <a href="https://code.google.com/p/google-web-toolkit/issues/detail?id=7750">https://code.google.com/p/google-web-toolkit/issues/detail?id=7750</a>
	 * 
	 * @param useClasspathForSrc true to add the src to the classpath, false to use -src parameters
	 */
	void setUseClasspathForSrc(Boolean useClasspathForSrc);

	Boolean getUseClasspathForSrc();

}