package de.richsource.gradle.plugins.gwt.internal;

import java.io.File;

public interface HasDirs {
	File getWar();

	void setWar(File war);

	File getDeploy();

	void setDeploy(File deploy);

	File getExtra();

	void setExtra(File extra);

	File getWorkDir();

	void setWorkDir(File workDir);

	File getGen();

	void setGen(File gen);
}
