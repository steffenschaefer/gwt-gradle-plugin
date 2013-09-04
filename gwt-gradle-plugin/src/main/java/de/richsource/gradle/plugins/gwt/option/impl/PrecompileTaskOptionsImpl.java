package de.richsource.gradle.plugins.gwt.option.impl;

import java.io.File;

import de.richsource.gradle.plugins.gwt.option.PrecompileTaskOptions;

public class PrecompileTaskOptionsImpl extends JJSOptionsImpl implements PrecompileTaskOptions {
	// -gen
	private File gen;
	private Boolean validateOnly;
	// -XdisableGeneratingOnShards
	private Boolean disableGeneratingOnShards;

	@Override
	public File getGen() {
		return gen;
	}

	@Override
	public void setGen(File gen) {
		this.gen = gen;
	}

	@Override
	public Boolean getValidateOnly() {
		return validateOnly;
	}

	@Override
	public void setValidateOnly(Boolean validateOnly) {
		this.validateOnly = validateOnly;
	}

	@Override
	public Boolean getDisableGeneratingOnShards() {
		return disableGeneratingOnShards;
	}

	@Override
	public void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards) {
		this.disableGeneratingOnShards = disableGeneratingOnShards;
	}

}
