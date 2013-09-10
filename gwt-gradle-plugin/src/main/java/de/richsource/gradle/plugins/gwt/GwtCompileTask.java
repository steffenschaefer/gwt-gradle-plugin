package de.richsource.gradle.plugins.gwt;

import java.io.File;

import org.gradle.api.tasks.OutputDirectory;

import de.richsource.gradle.plugins.gwt.internal.HasDirs;


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
public class GwtCompileTask extends AbstractGwtActionTask implements HasDirs {
	
	public static final String NAME = "compileGwt";
	
	@OutputDirectory
	private File war;
	private File deploy;
	private File extra;
	private File workDir;
	private File gen;
	
	private Integer localWorkers;
	private LogLevel logLevel;
	private Boolean draftCompile;
	private Boolean compileReport;
	
	private Boolean validateOnly;
	// -XdisableGeneratingOnShards
	private Boolean disableGeneratingOnShards;
	
	private Integer optimize;
	// -XdisableAggressiveOptimization
	private Boolean disableAggressiveOptimization;
	private Boolean disableClassMetadata;
	private Boolean disableCastChecking;
	private Boolean ea;
	// -XdisableRunAsync
	private Boolean disableRunAsync;
	private Style style;
	// -soyc
	private Boolean soyc;
	// -XsoycDetailed
	private Boolean soycDetailed;
	private Boolean strict;
	// -XdisableSoycHtml
	private Boolean disableSoycHtml;
	private Boolean enableClosureCompiler;
	// -XfragmentCount
	private Integer fragmentCount;

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.Compiler";
	}
	
	@Override
	protected void addArgs() {
		addDirArgs(this);
		
		argIfSet("-localWorkers", getLocalWorkers());
		argIfSet("-logLevel", getLogLevel());
		argIfEnabled(getDraftCompile(), "-draftCompile");
		argIfEnabled(getCompileReport(), "-compileReport");

		argIfEnabled(getValidateOnly(), "-validateOnly");
		argIfEnabled(getDisableGeneratingOnShards(), "-XdisableGeneratingOnShards");
		
		argIfSet("-optimize", getOptimize());
		argIfEnabled(getDisableAggressiveOptimization(), "-XdisableAggressiveOptimization");
		argIfEnabled(getDisableClassMetadata(), "-XdisableClassMetadata");
		argIfEnabled(getDisableCastChecking(), "-XdisableCastChecking");
		argIfEnabled(getEa(), "-ea");
		argIfEnabled(getDisableRunAsync(), "-XdisableRunAsync");
		argIfSet("-style", getStyle());
		argIfEnabled(getSoyc(), "-soyc");
		argIfEnabled(getSoycDetailed(), "-XsoycDetailed");
		argIfEnabled(getStrict(), "-strict");
		argIfEnabled(getDisableSoycHtml(), "-XdisableSoycHtml");
		argIfEnabled(getEnableClosureCompiler(), "-XenableClosureCompiler");
		argIfSet("-XfragmentCount", getFragmentCount());
	}

	/** {@inheritDoc} */
	@Override
	public File getWar() {
		return war;
	}

	/** {@inheritDoc} */
	@Override
	public void setWar(File war) {
		this.war = war;
	}

	/** {@inheritDoc} */
	@Override
	public File getDeploy() {
		return deploy;
	}

	/** {@inheritDoc} */
	@Override
	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	/** {@inheritDoc} */
	@Override
	public File getExtra() {
		return extra;
	}

	/** {@inheritDoc} */
	@Override
	public void setExtra(File extra) {
		this.extra = extra;
	}

	/** {@inheritDoc} */
	@Override
	public File getWorkDir() {
		return workDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}
	
	/** {@inheritDoc} */
	@Override
	public File getGen() {
		return gen;
	}

	/** {@inheritDoc} */
	@Override
	public void setGen(File gen) {
		this.gen = gen;
	}

	public Integer getLocalWorkers() {
		return localWorkers;
	}

	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public Boolean getDraftCompile() {
		return draftCompile;
	}

	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	public Boolean getCompileReport() {
		return compileReport;
	}

	public void setCompileReport(Boolean compileReport) {
		this.compileReport = compileReport;
	}

	public Boolean getValidateOnly() {
		return validateOnly;
	}

	public void setValidateOnly(Boolean validateOnly) {
		this.validateOnly = validateOnly;
	}

	public Boolean getDisableGeneratingOnShards() {
		return disableGeneratingOnShards;
	}

	public void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards) {
		this.disableGeneratingOnShards = disableGeneratingOnShards;
	}

	public Integer getOptimize() {
		return optimize;
	}

	public void setOptimize(Integer optimize) {
		this.optimize = optimize;
	}

	public Boolean getDisableAggressiveOptimization() {
		return disableAggressiveOptimization;
	}

	public void setDisableAggressiveOptimization(
			Boolean disableAggressiveOptimization) {
		this.disableAggressiveOptimization = disableAggressiveOptimization;
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

	public Boolean getEa() {
		return ea;
	}

	public void setEa(Boolean ea) {
		this.ea = ea;
	}

	public Boolean getDisableRunAsync() {
		return disableRunAsync;
	}

	public void setDisableRunAsync(Boolean disableRunAsync) {
		this.disableRunAsync = disableRunAsync;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Boolean getSoyc() {
		return soyc;
	}

	public void setSoyc(Boolean soyc) {
		this.soyc = soyc;
	}

	public Boolean getSoycDetailed() {
		return soycDetailed;
	}

	public void setSoycDetailed(Boolean soycDetailed) {
		this.soycDetailed = soycDetailed;
	}

	public Boolean getStrict() {
		return strict;
	}

	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	public Boolean getDisableSoycHtml() {
		return disableSoycHtml;
	}

	public void setDisableSoycHtml(Boolean disableSoycHtml) {
		this.disableSoycHtml = disableSoycHtml;
	}

	public Boolean getEnableClosureCompiler() {
		return enableClosureCompiler;
	}

	public void setEnableClosureCompiler(Boolean enableClosureCompiler) {
		this.enableClosureCompiler = enableClosureCompiler;
	}

	public Integer getFragmentCount() {
		return fragmentCount;
	}

	public void setFragmentCount(Integer fragmentCount) {
		this.fragmentCount = fragmentCount;
	}
}
