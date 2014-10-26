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
 * Defines the options known by the {@link GwtDev} task.
 */
public interface GwtDevOptions {

	Boolean getNoserver();

	/**
	 * Sets the "-noserver" flag that causes the GWT dev mode to not start the
	 * internal webserver (jetty) but only the code server that runs the GWT
	 * client part. The developer must ensure that an appropriate webserver is
	 * running to serve the static files/backend.
	 * 
	 * @param noserver
	 *            true if the "-noserver" flag should be set.
	 */
	void setNoserver(Boolean noserver);

	Integer getPort();

	/**
	 * Sets the "-port" option.
	 * 
	 * @param port Valid range is [1, 65535]
	 */
	void setPort(Integer port);

	String getWhitelist();

	/**
	 * Sets the "-whitelist" option
	 * 
	 * @param whitelist
	 */
	void setWhitelist(String whitelist);

	String getBlacklist();

	/**
	 * Sets the "-blacklist" option.
	 * 
	 * @param blacklist
	 */
	void setBlacklist(String blacklist);

	File getLogDir();

	/**
	 * Sets the "-logdir" option.
	 * 
	 * @param logDir
	 */
	void setLogDir(File logDir);

	String getBindAddress();

	/**
	 * Sets the "-bindAddress" option.
	 * 
	 * @param bindAddress
	 */
	void setBindAddress(String bindAddress);

	Integer getCodeServerPort();

	/**
	 * Sets the "-codeServerPort" option.
	 * 
	 * @param codeServerPort Valid range is [1, 65535]
	 */
	void setCodeServerPort(Integer codeServerPort);

	String getServer();

	/**
	 * Sets the "-server" option.
	 * 
	 * @param server
	 */
	void setServer(String server);

	String getStartupUrl();

	/**
	 * Sets the "-startupUrl" option.
	 * 
	 * @param startupUrl
	 */
	void setStartupUrl(String startupUrl);

	Boolean getAutoPort();

	/**
	 * Is set to true, this causes the "-port" to be automatically assigned using a free port.
	 * 
	 * @param autoPort
	 */
	void setAutoPort(Boolean autoPort);

	Boolean getAutoCodeServerPort();

	/**
	 * Is set to true, this causes the "-codeServerPort" to be automatically assigned using a free port.
	 * 
	 * @param autoCodeServerPort
	 */
	void setAutoCodeServerPort(Boolean autoCodeServerPort);
	
	Boolean getSuperDevMode();
	
	/**
	 * Is set to true, this causes the "-superDevMode" (added in GWT 2.7) flag to be added.
	 * 
	 * @param superDevMode
	 */
	void setSuperDevMode(Boolean superDevMode);
	
	Boolean getIncremental();
	
	/**
	 * Is set to true, this causes the "-incremental" (added in GWT 2.7) flag to be added.
	 * 
	 * @param incremental
	 */
	void setIncremental(Boolean incremental);
	
	Boolean getStartServer();
	
	/**
	 * Is set to true, this causes the "-startServer" (added in GWT 2.7) flag to be added.
	 * 
	 * @param startServer
	 */
	void setStartServer(Boolean startServer);
	
	JsInteropMode getJsInteropMode();
	
	/**
	 * Is set, this causes the "-XjsInteropMode" (added in GWT 2.7) flag to be added.
	 * 
	 * @param jsInteropMode
	 */
	void setJsInteropMode(JsInteropMode jsInteropMode);

}