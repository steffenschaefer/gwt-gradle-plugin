package de.richsource.gradle.plugins.gwt;

import java.io.File;


//-logLevel               The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//-workDir                The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
//-gen                    Debugging: causes normally-transient generated types to be saved in the specified directory
//-style                  Script output style: OBF[USCATED], PRETTY, or DETAILED (defaults to OBF)
//-ea                     Debugging: causes the compiled output to check assert statements
//-XdisableClassMetadata  EXPERIMENTAL: Disables some java.lang.Class methods (e.g. getName())
//-XdisableCastChecking   EXPERIMENTAL: Disables run-time checking of cast operations
//-validateOnly           Validate all source code, but do not compile
//-draftCompile           Enable faster, but less-optimized, compilations
//-optimize               Sets the optimization level used by the compiler.  0=none 9=maximum.
//-compileReport          Create a compile report that tells the Story of Your Compile
//-strict                 Only succeed if no input files have errors
//-localWorkers           The number of local workers to use when compiling permutations
//-war                    The directory into which deployable output files will be written (defaults to 'war')
//-deploy                 The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//-extra                  The directory into which extra files, not intended for deployment, will be written
public class GwtCompileTask extends AbstractGwtActionTask {
	
	public static final String NAME = "compileGwt";
	
//	private Boolean noserver;
//	private Integer port;
//	private String whitelist;
	
	private LogLevel logLevel;
	private File workDir;
	private File gen;
	private Style style;
	private Boolean ea;
	private Boolean enableClosureCompiler;
	private Boolean disableClassMetadata;
	private Boolean disableCastChecking;
	private Boolean validateOnly;
	private Boolean draftCompile;
	private Integer optimize;
	private Boolean compileReport;
	private Boolean strict;
	private Integer localWorkers;
	private File war;
	private File deploy;
	private File extra;

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.Compiler";
	}
	
	@Override
	protected void addArgs() {
		argIfSet("-logLevel", getLogLevel());
		dirArgIfSet("-workDir", getWorkDir());
		dirArgIfSet("-gen", getGen());
		argIfSet("-style", getStyle());
		argIfEnabled(getEa(), "-ea");
		argIfEnabled(getEnableClosureCompiler(), "-XenableClosureCompiler");
		argIfEnabled(getDisableClassMetadata(), "-XdisableClassMetadata");
		argIfEnabled(getDisableCastChecking(), "-XdisableCastChecking");
		argIfEnabled(getValidateOnly(), "-validateOnly");
		argIfEnabled(getDraftCompile(), "-draftCompile");
		argIfSet("-optimize", getOptimize());
		argIfEnabled(getCompileReport(), "-compileReport");
		argIfEnabled(getStrict(), "-strict");
		argIfSet("-localWorkers", getLocalWorkers());
		dirArgIfSet("-war", getWar());
		dirArgIfSet("-deploy", getDeploy());
		dirArgIfSet("-extra", getExtra());

		// Dev
		
//		  -noserver        Prevents the embedded web server from running
//		  -port            Specifies the TCP port for the embedded web server (defaults to 8888)
//		  -whitelist       Allows the user to browse URLs that match the specified regexes (comma or space separated)
//		  -blacklist       Prevents the user browsing URLs that match the specified regexes (comma or space separated)
//		  -logdir          Logs to a file in the given directory, as well as graphically
//		  -logLevel        The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//		  -gen             Debugging: causes normally-transient generated types to be saved in the specified directory
//		  -bindAddress     Specifies the bind address for the code server and web server (defaults to 127.0.0.1)
//		  -codeServerPort  Specifies the TCP port for the code server (defaults to 9997)
//		  -server          Specify a different embedded web server to run (must implement ServletContainerLauncher)
//		  -startupUrl      Automatically launches the specified URL
//		  -war             The directory into which deployable output files will be written (defaults to 'war')
//		  -deploy          The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//		  -extra           The directory into which extra files, not intended for deployment, will be written
//		  -workDir         The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
		
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
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

	public Boolean getValidateOnly() {
		return validateOnly;
	}

	public void setValidateOnly(Boolean validateOnly) {
		this.validateOnly = validateOnly;
	}

	public Boolean getDraftCompile() {
		return draftCompile;
	}

	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	public Integer getOptimize() {
		return optimize;
	}

	public void setOptimize(Integer optimize) {
		this.optimize = optimize;
	}

	public Boolean getCompileReport() {
		return compileReport;
	}

	public void setCompileReport(Boolean compileReport) {
		this.compileReport = compileReport;
	}

	public Boolean getStrict() {
		return strict;
	}

	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	public Integer getLocalWorkers() {
		return localWorkers;
	}

	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
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

	public Boolean getEnableClosureCompiler() {
		return enableClosureCompiler;
	}

	public void setEnableClosureCompiler(Boolean enableClosureCompiler) {
		this.enableClosureCompiler = enableClosureCompiler;
	}
}
