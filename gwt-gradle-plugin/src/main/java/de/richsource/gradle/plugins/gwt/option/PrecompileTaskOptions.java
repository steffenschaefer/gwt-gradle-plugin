package de.richsource.gradle.plugins.gwt.option;

import java.io.File;

public interface PrecompileTaskOptions extends JJSOptions {

	File getGen();

	void setGen(File gen);

	Boolean getValidateOnly();

	void setValidateOnly(Boolean validateOnly);

	Boolean getDisableGeneratingOnShards();

	void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards);
}