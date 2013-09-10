package de.richsource.gradle.plugins.gwt;

import java.io.File;

//-noserver        Prevents the embedded web server from running
//-port            Specifies the TCP port for the embedded web server (defaults to 8888)
//-whitelist       Allows the user to browse URLs that match the specified regexes (comma or space separated)
//-blacklist       Prevents the user browsing URLs that match the specified regexes (comma or space separated)
//-logdir          Logs to a file in the given directory, as well as graphically
//-logLevel        The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//-gen             Debugging: causes normally-transient generated types to be saved in the specified directory
//-bindAddress     Specifies the bind address for the code server and web server (defaults to 127.0.0.1)
//-codeServerPort  Specifies the TCP port for the code server (defaults to 9997)
//-server          Specify a different embedded web server to run (must implement ServletContainerLauncher)
//-startupUrl      Automatically launches the specified URL
//-war             The directory into which deployable output files will be written (defaults to 'war')
//-deploy          The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//-extra           The directory into which extra files, not intended for deployment, will be written
//-workDir         The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
public class GwtDev extends AbstractGwtTask {
	
	private Boolean noserver;
	private Integer port;
	private String whitelist;
	private String blacklist;
	private File logDir;
	private String bindAddress;
	private Integer codeServerPort;
	private String server;
	private String startupUrl;

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.DevMode";
	}
	
	@Override
	protected void addArgs() {
		super.addArgs();

		argIfEnabled(getNoserver(), "-noserver");
		argIfSet("-port", getPort());
		argIfSet("-whitelist", getWhitelist());
		argIfSet("-blacklist", getBlacklist());
		argIfSet("-logDir", getLogDir());
		argIfSet("-bindAddress", getBindAddress());
		argIfSet("-codeServerPort", getCodeServerPort());
		argIfSet("-server", getServer());
		argIfSet("-startupUrl", getStartupUrl());
	}

	public Boolean getNoserver() {
		return noserver;
	}

	public void setNoserver(Boolean noserver) {
		this.noserver = noserver;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getWhitelist() {
		return whitelist;
	}

	public void setWhitelist(String whitelist) {
		this.whitelist = whitelist;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

	public File getLogDir() {
		return logDir;
	}

	public void setLogDir(File logDir) {
		this.logDir = logDir;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	public Integer getCodeServerPort() {
		return codeServerPort;
	}

	public void setCodeServerPort(Integer codeServerPort) {
		this.codeServerPort = codeServerPort;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getStartupUrl() {
		return startupUrl;
	}

	public void setStartupUrl(String startupUrl) {
		this.startupUrl = startupUrl;
	}
}
