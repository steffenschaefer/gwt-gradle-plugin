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

public class GwtTestOptionsBase {
	private LogLevel logLevel;
	
	private Integer port;
	private Boolean autoPort;
	private String whitelist;
	private String blacklist;
	private File logDir;
	private Integer codeServerPort;
	private Boolean autoCodeServerPort;

	private Style style;
	private Boolean ea;
	private Boolean disableClassMetadata;
	private Boolean disableCastChecking;
	private Boolean draftCompile;
	private Integer localWorkers;
	private Boolean prod;
	private Integer testMethodTimeout;
	private Integer testBeginTimeout;
	private String runStyle;
	private Boolean notHeadless;
	private Boolean standardsMode;
	private Boolean quirksMode;
	// -Xtries
	private Integer tries;
	private String userAgents;

	public boolean isProd() {
		return prod;
	}

	public void setProd(boolean prod) {
		this.prod = prod;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getAutoPort() {
		return autoPort;
	}

	public void setAutoPort(Boolean autoPort) {
		this.autoPort = autoPort;
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

	public Integer getCodeServerPort() {
		return codeServerPort;
	}

	public void setCodeServerPort(Integer codeServerPort) {
		this.codeServerPort = codeServerPort;
	}

	public Boolean getAutoCodeServerPort() {
		return autoCodeServerPort;
	}

	public void setAutoCodeServerPort(Boolean autoCodeServerPort) {
		this.autoCodeServerPort = autoCodeServerPort;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Boolean getEa() {
		return ea;
	}

	public void setEa(Boolean ea) {
		this.ea = ea;
	}

	public Boolean getDisableClassMetadata() {
		return disableClassMetadata;
	}

	public void setDisableClassMetadata(Boolean disableClassMetadata) {
		this.disableClassMetadata = disableClassMetadata;
	}

	public Boolean getDisableCastChecking() {
		return disableCastChecking;
	}

	public void setDisableCastChecking(Boolean disableCastChecking) {
		this.disableCastChecking = disableCastChecking;
	}

	public Boolean getDraftCompile() {
		return draftCompile;
	}

	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	public Integer getLocalWorkers() {
		return localWorkers;
	}

	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
	}

	public Boolean getProd() {
		return prod;
	}

	public void setProd(Boolean prod) {
		this.prod = prod;
	}

	public Integer getTestMethodTimeout() {
		return testMethodTimeout;
	}

	public void setTestMethodTimeout(Integer testMethodTimeout) {
		this.testMethodTimeout = testMethodTimeout;
	}

	public Integer getTestBeginTimeout() {
		return testBeginTimeout;
	}

	public void setTestBeginTimeout(Integer testBeginTimeout) {
		this.testBeginTimeout = testBeginTimeout;
	}

	public Boolean getNotHeadless() {
		return notHeadless;
	}

	public void setNotHeadless(Boolean notHeadless) {
		this.notHeadless = notHeadless;
	}

	public Boolean getStandardsMode() {
		return standardsMode;
	}

	public void setStandardsMode(Boolean standardsMode) {
		this.standardsMode = standardsMode;
	}

	public Boolean getQuirksMode() {
		return quirksMode;
	}

	public void setQuirksMode(Boolean quirksMode) {
		this.quirksMode = quirksMode;
	}

	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}

	public String getUserAgents() {
		return userAgents;
	}

	public void setUserAgents(String userAgents) {
		this.userAgents = userAgents;
	}

	public String getRunStyle() {
		return runStyle;
	}

	public void setRunStyle(String runStyle) {
		this.runStyle = runStyle;
	}
}
