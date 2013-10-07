package de.richsource.gradle.plugins.gwt;

import java.io.File;

//Google Web Toolkit 2.5.1
//JUnitShell [-port port-number | "auto"] [-whitelist whitelist-string] [-blacklist blacklist-string] [-logdir directory] [-logLevel level] [-gen dir] [-codeServerPort port-number | "auto"] [-war dir] [-deploy dir] [-extra dir] [-workDir dir] [-style style] [-ea] [-XdisableClassMetadata] [-XdisableCastChecking] [-draftCompile] [-localWorkers count] [-prod] [-testMethodTimeout minutes] [-testBeginTimeout minutes] [-runStyle runstyle[:args]] [-notHeadless] [-standardsMode] [-quirksMode] [-Xtries 1] [-userAgents userAgents]
//where 
//  -port                   Specifies the TCP port for the embedded web server (defaults to 8888)
//  -whitelist              Allows the user to browse URLs that match the specified regexes (comma or space separated)
//  -blacklist              Prevents the user browsing URLs that match the specified regexes (comma or space separated)
//  -logdir                 Logs to a file in the given directory, as well as graphically
//  -logLevel               The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//  -gen                    Debugging: causes normally-transient generated types to be saved in the specified directory
//  -codeServerPort         Specifies the TCP port for the code server (defaults to 9997)
//  -war                    The directory into which deployable output files will be written (defaults to 'war')
//  -deploy                 The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//  -extra                  The directory into which extra files, not intended for deployment, will be written
//  -workDir                The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
//  -style                  Script output style: OBF[USCATED], PRETTY, or DETAILED (defaults to OBF)
//  -ea                     Debugging: causes the compiled output to check assert statements
//  -XdisableClassMetadata  EXPERIMENTAL: Disables some java.lang.Class methods (e.g. getName())
//  -XdisableCastChecking   EXPERIMENTAL: Disables run-time checking of cast operations
//  -draftCompile           Enable faster, but less-optimized, compilations
//  -localWorkers           The number of local workers to use when compiling permutations
//  -prod                   Causes your test to run in production (compiled) mode (defaults to development mode)
//  -testMethodTimeout      Set the test method timeout, in minutes
//  -testBeginTimeout       Set the test begin timeout (time for clients to contact server), in minutes
//  -runStyle               Selects the runstyle to use for this test.  The name is a suffix of com.google.gwt.junit.RunStyle or is a fully qualified class name, and may be followed with a colon and an argument for this runstyle.  The specified class mustextend RunStyle.
//  -notHeadless            Causes the log window and browser windows to be displayed; useful for debugging
//  -standardsMode          Run each test using an HTML document in standards mode (rather than quirks mode)
//  -quirksMode             Run each test using an HTML document in quirks mode (rather than standards mode)
//  -Xtries                 EXPERIMENTAL: Sets the maximum number of attempts for running each test method
//  -userAgents             Specify the user agents to reduce the number of permutations for remote browser tests; e.g. ie6,ie8,safari,gecko1_8,opera
public class GwtTestOptions {
	private boolean hasGwtTests = false;
	private File war;
	private File deploy;
	private File extra;
	private File workDir;
	private File gen;
//	private File cacheDir;
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
	// TODO -runStyle
	private Boolean notHeadless;
	private Boolean standardsMode;
	private Boolean quirksMode;
	// -Xtries
	private Integer tries;
	private String userAgents;
	
	protected String getParameterString() {
		final StringBuilder builder = new StringBuilder();
		
		dirArgIfSet(builder, "-war", getWar());
		dirArgIfSet(builder, "-deploy", getDeploy());
		dirArgIfSet(builder, "-extra", getExtra());
		dirArgIfSet(builder, "-workDir", getWorkDir());
		dirArgIfSet(builder, "-gen", getGen());
		
		argIfSet(builder, "-logLevel", getLogLevel());
		
		argIfSet(builder, "-port", Boolean.TRUE.equals(getAutoPort())? "auto" : getPort());
		argIfSet(builder, "-whitelist", getWhitelist());
		argIfSet(builder, "-blacklist", getBlacklist());
		argIfSet(builder, "-logdir", getLogDir());
		argIfSet(builder, "-codeServerPort", Boolean.TRUE.equals(getAutoCodeServerPort())? "auto" : getCodeServerPort());
		
		argIfSet(builder, "-style", getStyle());
		argIfEnabled(builder, getEa(), "-ea");
		argIfEnabled(builder, getDisableClassMetadata(), "-XdisableClassMetadata");
		argIfEnabled(builder, getDisableCastChecking(), "-XdisableCastChecking");
		argIfEnabled(builder, getDraftCompile(), "-draftCompile");
		argIfSet(builder, "-localWorkers", getLocalWorkers());
		argIfEnabled(builder, getProd(), "-prod");
		argIfSet(builder, "-testMethodTimeout", getTestMethodTimeout());
		argIfSet(builder, "-testBeginTimeout", getTestBeginTimeout());
		argIfEnabled(builder, getNotHeadless(), "-notHeadless");
		argIfEnabled(builder, getStandardsMode(), "-standardsMode");
		argIfEnabled(builder, getQuirksMode(), "-quirksMode");
		argIfSet(builder, "-Xtries", getTries());
		argIfSet(builder, "-userAgents", getUserAgents());
		
		return builder.toString();
	}
	
	private void argIfEnabled(StringBuilder builder, Boolean condition, String arg) {
		if (Boolean.TRUE.equals(condition)) {
			arg(builder, arg);
		}
	}

	private void dirArgIfSet(StringBuilder builder, String arg, File dir) {
		if (dir != null) {
			dir.mkdirs();
			arg(builder, arg, dir);
		}
	}

	private void argIfSet(StringBuilder builder, String arg, Object value) {
		if (value != null) {
			arg(builder, arg, value);
		}
	}
	
	private void arg(StringBuilder builder, Object... args) {
		for(Object arg : args) {
			if(builder.length() > 0) {
				builder.append(' ');
			}
			builder.append(arg.toString());
		}
	}

	public boolean isHasGwtTests() {
		return hasGwtTests;
	}

	public void setHasGwtTests(boolean hasGwtTests) {
		this.hasGwtTests = hasGwtTests;
	}

	public boolean isProd() {
		return prod;
	}

	public void setProd(boolean prod) {
		this.prod = prod;
	}

	public File getWar() {
		return war;
	}

	public void setWar(File war) {
		this.war = war;
	}

	public File getDeploy() {
		return deploy;
	}

	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	public File getExtra() {
		return extra;
	}

	public void setExtra(File extra) {
		this.extra = extra;
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	public File getGen() {
		return gen;
	}

	public void setGen(File gen) {
		this.gen = gen;
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
}
