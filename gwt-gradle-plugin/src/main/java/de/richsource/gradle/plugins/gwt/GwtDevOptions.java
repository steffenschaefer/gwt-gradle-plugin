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

	void setPort(Integer port);

	String getWhitelist();

	void setWhitelist(String whitelist);

	String getBlacklist();

	void setBlacklist(String blacklist);

	File getLogDir();

	void setLogDir(File logDir);

	String getBindAddress();

	void setBindAddress(String bindAddress);

	Integer getCodeServerPort();

	void setCodeServerPort(Integer codeServerPort);

	String getServer();

	void setServer(String server);

	String getStartupUrl();

	void setStartupUrl(String startupUrl);

	Boolean getAutoPort();

	void setAutoPort(Boolean autoPort);

	Boolean getAutoCodeServerPort();

	void setAutoCodeServerPort(Boolean autoCodeServerPort);

}