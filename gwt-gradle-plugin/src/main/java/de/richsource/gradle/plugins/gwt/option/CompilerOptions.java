package de.richsource.gradle.plugins.gwt.option;

import java.io.File;

public interface CompilerOptions extends PrecompileTaskOptions {

	File getWar();

	void setWar(File war);

	File getDeploy();

	void setDeploy(File deploy);

	File getExtra();

	void setExtra(File extra);

	File getOut();

	void setOut(File out);

	File getWorkDir();

	void setWorkDir(File workDir);

	Integer getLocalWorkers();

	void setLocalWorkers(Integer localWorkers);

	LogLevel getLogLevel();

	void setLogLevel(LogLevel logLevel);

	Boolean getDraftCompile();

	void setDraftCompile(Boolean draftCompile);

	Boolean getCompileReport();

	void setCompileReport(Boolean compileReport);
}