package de.richsource.gradle.plugins.gwt.option.impl;

import java.io.File;

import org.gradle.api.tasks.OutputDirectory;

import de.richsource.gradle.plugins.gwt.option.CompilerOptions;
import de.richsource.gradle.plugins.gwt.option.LogLevel;

public class CompilerOptionsImpl extends PrecompileTaskOptionsImpl implements
		CompilerOptions {

	@OutputDirectory
	private File war;
	@OutputDirectory
	private File deploy;
	@OutputDirectory
	private File extra;
	@OutputDirectory
	private File workDir;
	private Integer localWorkers;
	private LogLevel logLevel;
	private Boolean draftCompile;
	private Boolean compileReport;

	@Override
	public File getWar() {
		return war;
	}

	@Override
	public void setWar(File war) {
		this.war = war;
	}

	@Override
	public File getDeploy() {
		return deploy;
	}

	@Override
	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	@Override
	public File getExtra() {
		return extra;
	}

	@Override
	public void setExtra(File extra) {
		this.extra = extra;
	}

	@Override
	public File getWorkDir() {
		return workDir;
	}

	@Override
	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}

	@Override
	public Integer getLocalWorkers() {
		return localWorkers;
	}

	@Override
	public void setLocalWorkers(Integer localWorkers) {
		this.localWorkers = localWorkers;
	}

	@Override
	public LogLevel getLogLevel() {
		return logLevel;
	}

	@Override
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public Boolean getDraftCompile() {
		return draftCompile;
	}

	@Override
	public void setDraftCompile(Boolean draftCompile) {
		this.draftCompile = draftCompile;
	}

	@Override
	public Boolean getCompileReport() {
		return compileReport;
	}

	@Override
	public void setCompileReport(Boolean compileReport) {
		this.compileReport = compileReport;
	}

}