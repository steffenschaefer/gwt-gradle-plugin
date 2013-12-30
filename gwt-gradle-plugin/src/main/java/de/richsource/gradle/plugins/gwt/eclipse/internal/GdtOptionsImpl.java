package de.richsource.gradle.plugins.gwt.eclipse.internal;

import java.io.File;

import de.richsource.gradle.plugins.gwt.eclipse.GdtOptions;

public class GdtOptionsImpl implements GdtOptions {
	private File lastWarOutDir;
	private File warSrcDir;
	private Boolean warSrcDirIsOutput;

	/** {@inheritDoc} */
	@Override
	public File getLastWarOutDir() {
		return lastWarOutDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setLastWarOutDir(File lastWarOutDir) {
		this.lastWarOutDir = lastWarOutDir;
	}

	/** {@inheritDoc} */
	@Override
	public File getWarSrcDir() {
		return warSrcDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDir(File warSrcDir) {
		this.warSrcDir = warSrcDir;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getWarSrcDirIsOutput() {
		return warSrcDirIsOutput;
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDirIsOutput(Boolean warSrcDirIsOutput) {
		this.warSrcDirIsOutput = warSrcDirIsOutput;
	}

}
