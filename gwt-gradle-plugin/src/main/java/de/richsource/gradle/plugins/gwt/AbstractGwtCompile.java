package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.concurrent.Callable;

import org.gradle.api.tasks.OutputDirectory;


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
public class AbstractGwtCompile extends AbstractGwtTask {
	
	private final GwtCompileOptions options = new GwtCompileOptions();

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.Compiler";
	}
	
	@Override
	protected void addArgs() {
		super.addArgs();
		
		argIfSet("-localWorkers", getLocalWorkers());
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
	
	protected void configure(final GwtCompileOptions options) {
		conventionMapping("localWorkers", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getLocalWorkers();
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	@OutputDirectory
	public File getWar() {
		return super.getWar();
	}

	public Integer getLocalWorkers() {
		return options.getLocalWorkers();
	}

	public void setLocalWorkers(Integer localWorkers) {
		options.setLocalWorkers(localWorkers);
	}

	public Boolean getDraftCompile() {
		return options.getDraftCompile();
	}

	public void setDraftCompile(Boolean draftCompile) {
		options.setDraftCompile(draftCompile);
	}

	public Boolean getCompileReport() {
		return options.getCompileReport();
	}

	public void setCompileReport(Boolean compileReport) {
		options.setCompileReport(compileReport);
	}

	public Boolean getValidateOnly() {
		return options.getValidateOnly();
	}

	public void setValidateOnly(Boolean validateOnly) {
		options.setValidateOnly(validateOnly);
	}

	public Boolean getDisableGeneratingOnShards() {
		return options.getDisableGeneratingOnShards();
	}

	public void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards) {
		options.setDisableGeneratingOnShards(disableGeneratingOnShards);
	}

	public Integer getOptimize() {
		return options.getOptimize();
	}

	public void setOptimize(Integer optimize) {
		options.setOptimize(optimize);
	}

	public Boolean getDisableAggressiveOptimization() {
		return options.getDisableAggressiveOptimization();
	}

	public void setDisableAggressiveOptimization(
			Boolean disableAggressiveOptimization) {
		options.setDisableAggressiveOptimization(disableAggressiveOptimization);
	}

	public Boolean getDisableClassMetadata() {
		return options.getDisableClassMetadata();
	}

	public void setDisableClassMetadata(Boolean disableClassMetadata) {
		options.setDisableClassMetadata(disableClassMetadata);
	}

	public Boolean getDisableCastChecking() {
		return options.getDisableCastChecking();
	}

	public void setDisableCastChecking(Boolean disableCastChecking) {
		options.setDisableCastChecking(disableCastChecking);
	}

	public Boolean getEa() {
		return options.getEa();
	}

	public void setEa(Boolean ea) {
		options.setEa(ea);
	}

	public Boolean getDisableRunAsync() {
		return options.getDisableRunAsync();
	}

	public void setDisableRunAsync(Boolean disableRunAsync) {
		options.setDisableRunAsync(disableRunAsync);
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
