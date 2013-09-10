package de.richsource.gradle.plugins.gwt;

import java.io.File;

public abstract class AbstractGwtActionTaskWithDirs extends AbstractGwtActionTask {
	private File war;
	private File deploy;
	private File extra;
	private File workDir;
	private File gen;
	
	protected void addDirArgs() {
		dirArgIfSet("-war", getWar());
		dirArgIfSet("-deploy", getDeploy());
		dirArgIfSet("-extra", getExtra());
		dirArgIfSet("-workDir", getWorkDir());
		dirArgIfSet("-gen", getGen());
	}
	
	public File getWar() {
		return war;
	}

	public void setWar(File war) {
		this.war = war;
	}

	public File getDeploy() {
		return deploy;
	}

	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	public File getExtra() {
		return extra;
	}

	public void setExtra(File extra) {
		this.extra = extra;
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}
	
	public File getGen() {
		return gen;
	}

	public void setGen(File gen) {
		this.gen = gen;
	}
}
