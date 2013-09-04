package de.richsource.gradle.plugins.gwt.option.impl;

import de.richsource.gradle.plugins.gwt.option.JJSOptions;
import de.richsource.gradle.plugins.gwt.option.Style;


public class JJSOptionsImpl implements JJSOptions {
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

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getOptimize()
	 */
	@Override
	public Integer getOptimize() {
		return optimize;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setOptimize(java.lang.Integer)
	 */
	@Override
	public void setOptimize(Integer optimize) {
		this.optimize = optimize;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getDisableAggressiveOptimization()
	 */
	@Override
	public Boolean getDisableAggressiveOptimization() {
		return disableAggressiveOptimization;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setDisableAggressiveOptimization(java.lang.Boolean)
	 */
	@Override
	public void setDisableAggressiveOptimization(
			Boolean disableAggressiveOptimization) {
		this.disableAggressiveOptimization = disableAggressiveOptimization;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getDisableClassMetadata()
	 */
	@Override
	public Boolean getDisableClassMetadata() {
		return disableClassMetadata;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setDisableClassMetadata(java.lang.Boolean)
	 */
	@Override
	public void setDisableClassMetadata(Boolean disableClassMetadata) {
		this.disableClassMetadata = disableClassMetadata;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getDisableCastChecking()
	 */
	@Override
	public Boolean getDisableCastChecking() {
		return disableCastChecking;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setDisableCastChecking(java.lang.Boolean)
	 */
	@Override
	public void setDisableCastChecking(Boolean disableCastChecking) {
		this.disableCastChecking = disableCastChecking;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getEa()
	 */
	@Override
	public Boolean getEa() {
		return ea;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setEa(java.lang.Boolean)
	 */
	@Override
	public void setEa(Boolean ea) {
		this.ea = ea;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getDisableRunAsync()
	 */
	@Override
	public Boolean getDisableRunAsync() {
		return disableRunAsync;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setDisableRunAsync(java.lang.Boolean)
	 */
	@Override
	public void setDisableRunAsync(Boolean disableRunAsync) {
		this.disableRunAsync = disableRunAsync;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getStyle()
	 */
	@Override
	public Style getStyle() {
		return style;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setStyle(de.richsource.gradle.plugins.gwt.Style)
	 */
	@Override
	public void setStyle(Style style) {
		this.style = style;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getSoyc()
	 */
	@Override
	public Boolean getSoyc() {
		return soyc;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setSoyc(java.lang.Boolean)
	 */
	@Override
	public void setSoyc(Boolean soyc) {
		this.soyc = soyc;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getSoycDetailed()
	 */
	@Override
	public Boolean getSoycDetailed() {
		return soycDetailed;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setSoycDetailed(java.lang.Boolean)
	 */
	@Override
	public void setSoycDetailed(Boolean soycDetailed) {
		this.soycDetailed = soycDetailed;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getStrict()
	 */
	@Override
	public Boolean getStrict() {
		return strict;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setStrict(java.lang.Boolean)
	 */
	@Override
	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getDisableSoycHtml()
	 */
	@Override
	public Boolean getDisableSoycHtml() {
		return disableSoycHtml;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setDisableSoycHtml(java.lang.Boolean)
	 */
	@Override
	public void setDisableSoycHtml(Boolean disableSoycHtml) {
		this.disableSoycHtml = disableSoycHtml;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getEnableClosureCompiler()
	 */
	@Override
	public Boolean getEnableClosureCompiler() {
		return enableClosureCompiler;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setEnableClosureCompiler(java.lang.Boolean)
	 */
	@Override
	public void setEnableClosureCompiler(Boolean enableClosureCompiler) {
		this.enableClosureCompiler = enableClosureCompiler;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#getFragmentCount()
	 */
	@Override
	public Integer getFragmentCount() {
		return fragmentCount;
	}

	/* (non-Javadoc)
	 * @see de.richsource.gradle.plugins.gwt.option.impl.Foo#setFragmentCount(java.lang.Integer)
	 */
	@Override
	public void setFragmentCount(Integer fragmentCount) {
		this.fragmentCount = fragmentCount;
	}
}
