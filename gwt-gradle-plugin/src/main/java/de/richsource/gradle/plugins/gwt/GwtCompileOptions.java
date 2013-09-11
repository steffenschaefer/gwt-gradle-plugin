package de.richsource.gradle.plugins.gwt;


public interface GwtCompileOptions {

	Integer getLocalWorkers();

	void setLocalWorkers(Integer localWorkers);

	Boolean getDraftCompile();

	void setDraftCompile(Boolean draftCompile);

	Boolean getCompileReport();

	void setCompileReport(Boolean compileReport);

	Boolean getCompilerMetrics();

	void setCompilerMetrics(Boolean compilerMetrics);

	Boolean getValidateOnly();

	void setValidateOnly(Boolean validateOnly);

	Boolean getDisableGeneratingOnShards();

	void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards);

	Integer getOptimize();

	void setOptimize(Integer optimize);

	Boolean getDisableAggressiveOptimization();

	void setDisableAggressiveOptimization(Boolean disableAggressiveOptimization);

	Boolean getDisableClassMetadata();

	void setDisableClassMetadata(Boolean disableClassMetadata);

	Boolean getDisableCastChecking();

	void setDisableCastChecking(Boolean disableCastChecking);

	Boolean getEa();

	void setEa(Boolean ea);

	Boolean getDisableRunAsync();

	void setDisableRunAsync(Boolean disableRunAsync);

	Style getStyle();

	void setStyle(Style style);

	Boolean getSoycDetailed();

	void setSoycDetailed(Boolean soycDetailed);

	Boolean getStrict();

	void setStrict(Boolean strict);

	Boolean getDisableSoycHtml();

	void setDisableSoycHtml(Boolean disableSoycHtml);

	Boolean getEnableClosureCompiler();

	void setEnableClosureCompiler(Boolean enableClosureCompiler);

	Integer getFragmentCount();

	void setFragmentCount(Integer fragmentCount);

}