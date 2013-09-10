package de.richsource.gradle.plugins.gwt;


public class GwtCompileOptions {
	private Integer localWorkers;
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

	public Integer getLocalWorkers() {
		return localWorkers;
	}

	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
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
