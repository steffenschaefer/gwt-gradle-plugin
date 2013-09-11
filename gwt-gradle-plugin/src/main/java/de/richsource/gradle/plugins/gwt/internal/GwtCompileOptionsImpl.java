package de.richsource.gradle.plugins.gwt.internal;

import de.richsource.gradle.plugins.gwt.GwtCompileOptions;
import de.richsource.gradle.plugins.gwt.Style;


/**
 * Default implementation of {@link GwtCompileOptions}.
 */
public class GwtCompileOptionsImpl implements GwtCompileOptions {
	private Integer localWorkers;
	private Boolean draftCompile;
	private Boolean compileReport;
	// -XcompilerMetrics
	private Boolean compilerMetrics;
	
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
	// -XsoycDetailed
	private Boolean soycDetailed;
	private Boolean strict;
	// -XdisableSoycHtml
	private Boolean disableSoycHtml;
	private Boolean enableClosureCompiler;
	// -XfragmentCount
	private Integer fragmentCount;

	/** {@inheritDoc} */
	@Override
	public Integer getLocalWorkers() {
		return localWorkers;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDraftCompile() {
		return draftCompile;
	}

	/** {@inheritDoc} */
	@Override
	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getCompileReport() {
		return compileReport;
	}

	/** {@inheritDoc} */
	@Override
	public void setCompileReport(Boolean compileReport) {
		this.compileReport = compileReport;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getCompilerMetrics() {
		return compilerMetrics;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCompilerMetrics(Boolean compilerMetrics) {
		this.compilerMetrics = compilerMetrics;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValidateOnly() {
		return validateOnly;
	}

	/** {@inheritDoc} */
	@Override
	public void setValidateOnly(Boolean validateOnly) {
		this.validateOnly = validateOnly;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableGeneratingOnShards() {
		return disableGeneratingOnShards;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards) {
		this.disableGeneratingOnShards = disableGeneratingOnShards;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getOptimize() {
		return optimize;
	}

	/** {@inheritDoc} */
	@Override
	public void setOptimize(Integer optimize) {
		this.optimize = optimize;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableAggressiveOptimization() {
		return disableAggressiveOptimization;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableAggressiveOptimization(
			Boolean disableAggressiveOptimization) {
		this.disableAggressiveOptimization = disableAggressiveOptimization;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableClassMetadata() {
		return disableClassMetadata;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableClassMetadata(Boolean disableClassMetadata) {
		this.disableClassMetadata = disableClassMetadata;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableCastChecking() {
		return disableCastChecking;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableCastChecking(Boolean disableCastChecking) {
		this.disableCastChecking = disableCastChecking;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getEa() {
		return ea;
	}

	/** {@inheritDoc} */
	@Override
	public void setEa(Boolean ea) {
		this.ea = ea;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableRunAsync() {
		return disableRunAsync;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableRunAsync(Boolean disableRunAsync) {
		this.disableRunAsync = disableRunAsync;
	}

	/** {@inheritDoc} */
	@Override
	public Style getStyle() {
		return style;
	}

	/** {@inheritDoc} */
	@Override
	public void setStyle(Style style) {
		this.style = style;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getSoycDetailed() {
		return soycDetailed;
	}

	/** {@inheritDoc} */
	@Override
	public void setSoycDetailed(Boolean soycDetailed) {
		this.soycDetailed = soycDetailed;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getStrict() {
		return strict;
	}

	/** {@inheritDoc} */
	@Override
	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDisableSoycHtml() {
		return disableSoycHtml;
	}

	/** {@inheritDoc} */
	@Override
	public void setDisableSoycHtml(Boolean disableSoycHtml) {
		this.disableSoycHtml = disableSoycHtml;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getEnableClosureCompiler() {
		return enableClosureCompiler;
	}

	/** {@inheritDoc} */
	@Override
	public void setEnableClosureCompiler(Boolean enableClosureCompiler) {
		this.enableClosureCompiler = enableClosureCompiler;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getFragmentCount() {
		return fragmentCount;
	}

	/** {@inheritDoc} */
	@Override
	public void setFragmentCount(Integer fragmentCount) {
		this.fragmentCount = fragmentCount;
	}
}
