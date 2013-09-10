package de.richsource.gradle.plugins.gwt;

import java.io.File;

public class GwtDevOptions {
	private Boolean noserver;
	private Integer port;
	private Boolean autoPort;
	private String whitelist;
	private String blacklist;
	private File logDir;
	private String bindAddress;
	private Integer codeServerPort;
	private Boolean autoCodeServerPort;
	private String server;
	private String startupUrl;

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

	public Boolean getAutoPort() {
		return autoPort;
	}

	public void setAutoPort(Boolean autoPort) {
		this.autoPort = autoPort;
	}

	public Boolean getAutoCodeServerPort() {
		return autoCodeServerPort;
	}

	public void setAutoCodeServerPort(Boolean autoCodeServerPort) {
		this.autoCodeServerPort = autoCodeServerPort;
	}
}
