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
package de.richsource.gradle.plugins.gwt.internal;

import java.io.File;

import de.richsource.gradle.plugins.gwt.GwtDevOptions;

/**
 * Default implementation of {@link GwtDevOptions}.
 */
public class GwtDevOptionsImpl implements GwtDevOptions {
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
	private Boolean superDevMode;
	private Boolean startServer;

	/** {@inheritDoc} */
	@Override
	public Boolean getNoserver() {
		return noserver;
	}

	/** {@inheritDoc} */
	@Override
	public void setNoserver(Boolean noserver) {
		this.noserver = noserver;
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
	public String getWhitelist() {
		return whitelist;
	}

	/** {@inheritDoc} */
	@Override
	public void setWhitelist(String whitelist) {
		this.whitelist = whitelist;
	}

	/** {@inheritDoc} */
	@Override
	public String getBlacklist() {
		return blacklist;
	}

	/** {@inheritDoc} */
	@Override
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

	/** {@inheritDoc} */
	@Override
	public File getLogDir() {
		return logDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setLogDir(File logDir) {
		this.logDir = logDir;
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
	public Integer getCodeServerPort() {
		return codeServerPort;
	}

	/** {@inheritDoc} */
	@Override
	public void setCodeServerPort(Integer codeServerPort) {
		this.codeServerPort = codeServerPort;
	}

	/** {@inheritDoc} */
	@Override
	public String getServer() {
		return server;
	}

	/** {@inheritDoc} */
	@Override
	public void setServer(String server) {
		this.server = server;
	}

	/** {@inheritDoc} */
	@Override
	public String getStartupUrl() {
		return startupUrl;
	}

	/** {@inheritDoc} */
	@Override
	public void setStartupUrl(String startupUrl) {
		this.startupUrl = startupUrl;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAutoPort() {
		return autoPort;
	}

	/** {@inheritDoc} */
	@Override
	public void setAutoPort(Boolean autoPort) {
		this.autoPort = autoPort;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAutoCodeServerPort() {
		return autoCodeServerPort;
	}

	/** {@inheritDoc} */
	@Override
	public void setAutoCodeServerPort(Boolean autoCodeServerPort) {
		this.autoCodeServerPort = autoCodeServerPort;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getSuperDevMode() {
		return superDevMode;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSuperDevMode(Boolean superDevMode) {
		this.superDevMode = superDevMode;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getStartServer() {
		return startServer;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStartServer(Boolean startServer) {
		this.startServer = startServer;
	}
}
