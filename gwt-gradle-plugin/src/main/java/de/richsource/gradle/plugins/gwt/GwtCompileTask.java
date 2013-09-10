package de.richsource.gradle.plugins.gwt;

import java.io.File;

import de.richsource.gradle.plugins.gwt.option.CompilerOptions;
import de.richsource.gradle.plugins.gwt.option.LogLevel;
import de.richsource.gradle.plugins.gwt.option.Style;
import de.richsource.gradle.plugins.gwt.option.impl.CompilerOptionsImpl;


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
public class GwtCompileTask extends AbstractGwtActionTask implements CompilerOptions {
	
	public static final String NAME = "compileGwt";
	
	private CompilerOptions options = new CompilerOptionsImpl();

//	private Boolean noserver;
//	private Integer port;
//	private String whitelist;

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.Compiler";
	}
	
	private void addJJSOptions() {
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
	
	private void addPrecompileOptions() {
		addJJSOptions();
		dirArgIfSet("-gen", getGen());
		argIfEnabled(getValidateOnly(), "-validateOnly");
		argIfEnabled(getDisableGeneratingOnShards(), "-XdisableGeneratingOnShards");
	}
	
	@Override
	protected void addArgs() {
		addPrecompileOptions();
		
		dirArgIfSet("-war", getWar());
		dirArgIfSet("-deploy", getDeploy());
		dirArgIfSet("-extra", getExtra());
		dirArgIfSet("-workDir", getWorkDir());
		argIfSet("-localWorkers", getLocalWorkers());
		argIfSet("-logLevel", getLogLevel());
		argIfEnabled(getDraftCompile(), "-draftCompile");
		argIfEnabled(getCompileReport(), "-compileReport");
	}

	public Integer getOptimize() {
		return options.getOptimize();
	}

	public File getGen() {
		return options.getGen();
	}

	public File getWar() {
		return options.getWar();
	}

	public void setGen(File gen) {
		options.setGen(gen);
	}

	public void setOptimize(Integer optimize) {
		options.setOptimize(optimize);
	}

	public void setWar(File war) {
		options.setWar(war);
	}

	public Boolean getValidateOnly() {
		return options.getValidateOnly();
	}

	public File getDeploy() {
		return options.getDeploy();
	}

	public Boolean getDisableAggressiveOptimization() {
		return options.getDisableAggressiveOptimization();
	}

	public void setDeploy(File deploy) {
		options.setDeploy(deploy);
	}

	public void setValidateOnly(Boolean validateOnly) {
		options.setValidateOnly(validateOnly);
	}

	public File getExtra() {
		return options.getExtra();
	}

	public void setDisableAggressiveOptimization(
			Boolean disableAggressiveOptimization) {
		options.setDisableAggressiveOptimization(disableAggressiveOptimization);
	}

	public Boolean getDisableGeneratingOnShards() {
		return options.getDisableGeneratingOnShards();
	}

	public void setExtra(File extra) {
		options.setExtra(extra);
	}

	public void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards) {
		options.setDisableGeneratingOnShards(disableGeneratingOnShards);
	}

	public Boolean getDisableClassMetadata() {
		return options.getDisableClassMetadata();
	}

	public File getWorkDir() {
		return options.getWorkDir();
	}

	public void setWorkDir(File workDir) {
		options.setWorkDir(workDir);
	}

	public void setDisableClassMetadata(Boolean disableClassMetadata) {
		options.setDisableClassMetadata(disableClassMetadata);
	}

	public Integer getLocalWorkers() {
		return options.getLocalWorkers();
	}

	public void setLocalWorkers(Integer localWorkers) {
		options.setLocalWorkers(localWorkers);
	}

	public Boolean getDisableCastChecking() {
		return options.getDisableCastChecking();
	}

	public LogLevel getLogLevel() {
		return options.getLogLevel();
	}

	public void setDisableCastChecking(Boolean disableCastChecking) {
		options.setDisableCastChecking(disableCastChecking);
	}

	public void setLogLevel(LogLevel logLevel) {
		options.setLogLevel(logLevel);
	}

	public Boolean getEa() {
		return options.getEa();
	}

	public Boolean getDraftCompile() {
		return options.getDraftCompile();
	}

	public void setEa(Boolean ea) {
		options.setEa(ea);
	}

	public void setDraftCompile(Boolean draftCompile) {
		options.setDraftCompile(draftCompile);
	}

	public Boolean getDisableRunAsync() {
		return options.getDisableRunAsync();
	}

	public Boolean getCompileReport() {
		return options.getCompileReport();
	}

	public void setDisableRunAsync(Boolean disableRunAsync) {
		options.setDisableRunAsync(disableRunAsync);
	}

	public void setCompileReport(Boolean compileReport) {
		options.setCompileReport(compileReport);
	}

	public Style getStyle() {
		return options.getStyle();
	}

	public void setStyle(Style style) {
		options.setStyle(style);
	}

	public Boolean getSoyc() {
		return options.getSoyc();
	}

	public void setSoyc(Boolean soyc) {
		options.setSoyc(soyc);
	}

	public Boolean getSoycDetailed() {
		return options.getSoycDetailed();
	}

	public void setSoycDetailed(Boolean soycDetailed) {
		options.setSoycDetailed(soycDetailed);
	}

	public Boolean getStrict() {
		return options.getStrict();
	}

	public void setStrict(Boolean strict) {
		options.setStrict(strict);
	}

	public Boolean getDisableSoycHtml() {
		return options.getDisableSoycHtml();
	}

	public void setDisableSoycHtml(Boolean disableSoycHtml) {
		options.setDisableSoycHtml(disableSoycHtml);
	}

	public Boolean getEnableClosureCompiler() {
		return options.getEnableClosureCompiler();
	}

	public void setEnableClosureCompiler(Boolean enableClosureCompiler) {
		options.setEnableClosureCompiler(enableClosureCompiler);
	}

	public Integer getFragmentCount() {
		return options.getFragmentCount();
	}

	public void setFragmentCount(Integer fragmentCount) {
		options.setFragmentCount(fragmentCount);
	}
}
