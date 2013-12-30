package de.richsource.gradle.plugins.gwt.eclipse;

import java.io.File;

public interface GdtOptions {

	void setWarSrcDirIsOutput(Boolean warSrcDirIsOutput);

	Boolean getWarSrcDirIsOutput();

	void setWarSrcDir(File warSrcDir);

	File getWarSrcDir();

	void setLastWarOutDir(File lastWarOutDir);

	File getLastWarOutDir();

}
