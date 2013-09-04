package de.richsource.gradle.plugins.gwt.option;


public interface JJSOptions {

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

	Boolean getSoyc();

	void setSoyc(Boolean soyc);

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