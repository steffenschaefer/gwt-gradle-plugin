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