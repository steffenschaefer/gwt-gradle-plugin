/**
 * Copyright (C) 2013-2014 Steffen Schaefer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.List;


/**
 * Defines the options known by the {@link GwtCompile} and {@link GwtDraftCompile} tasks.
 */
public interface GwtCompileOptions {

	Integer getLocalWorkers();

	/**
	 * Sets the "-localWorkers" option.
	 * 
	 * @param localWorkers
	 */
	void setLocalWorkers(Integer localWorkers);

	Boolean getDraftCompile();

	/**
	 * If set to true, this adds the "-draftCompile" flag.
	 * 
	 * @param draftCompile
	 */
	void setDraftCompile(Boolean draftCompile);

	Boolean getCompileReport();

	/**
	 * If set to true, this adds the "-compileReport" flag.
	 * 
	 * @param compileReport
	 */
	void setCompileReport(Boolean compileReport);

	Boolean getCompilerMetrics();

	/**
	 * If set to true, this adds the "-XcompilerMetrics" flag.
	 * 
	 * @param compilerMetrics
	 */
	void setCompilerMetrics(Boolean compilerMetrics);

	Boolean getValidateOnly();

	/**
	 * If set to true, this adds the "-validateOnly" flag.
	 * 
	 * @param validateOnly
	 */
	void setValidateOnly(Boolean validateOnly);

	Boolean getDisableGeneratingOnShards();

	/**
	 * If set to true, this adds the "-XdisableGeneratingOnShards" flag.
	 * 
	 * @param disableGeneratingOnShards
	 */
	void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards);

	Integer getOptimize();

	/**
	 * Sets the "-optimize" option.
	 * 
	 * @param optimize the optimization level to set. Valid values are in the interval [0, 9].
	 */
	void setOptimize(Integer optimize);

	Boolean getDisableAggressiveOptimization();

	/**
	 * If set to true, this adds the "-XdisableAggressiveOptimization" flag.
	 * 
	 * @param disableAggressiveOptimization
	 */
	void setDisableAggressiveOptimization(Boolean disableAggressiveOptimization);

	Boolean getDisableClassMetadata();

	/**
	 * If set to true, this adds the "-XdisableClassMetadata" flag.
	 * 
	 * @param disableClassMetadata
	 */
	void setDisableClassMetadata(Boolean disableClassMetadata);

	Boolean getDisableCastChecking();

	/**
	 * If set to true, this adds the "-XdisableCastChecking" flag.
	 * 
	 * @param disableCastChecking
	 */
	void setDisableCastChecking(Boolean disableCastChecking);

	Boolean getEa();

	/**
	 * If set to true, this adds the "-ea" (enable assertions) flag.
	 * 
	 * @param ea
	 */
	void setEa(Boolean ea);

	Boolean getDisableRunAsync();

	/**
	 * If set to true, this adds the "-XdisableRunAsync" flag.
	 * 
	 * @param disableRunAsync
	 */
	void setDisableRunAsync(Boolean disableRunAsync);

	Style getStyle();

	/**
	 * Sets the "-style" option.
	 * 
	 * @param style
	 */
	void setStyle(Style style);

	Boolean getSoycDetailed();

	/**
	 * If set to true, this adds the "-XsoycDetailed" flag.
	 * 
	 * @param soycDetailed
	 */
	void setSoycDetailed(Boolean soycDetailed);

	Boolean getStrict();

	/**
	 * If set to true, this adds the "-strict" flag.
	 * 
	 * @param strict
	 */
	void setStrict(Boolean strict);

	Boolean getDisableSoycHtml();

	/**
	 * If set to true, this adds the "-XdisableSoycHtml" flag.
	 * 
	 * @param disableSoycHtml
	 */
	void setDisableSoycHtml(Boolean disableSoycHtml);

	Boolean getEnableClosureCompiler();

	/**
	 * If set to true, this adds the "-XenableClosureCompiler" flag.
	 * 
	 * @param enableClosureCompiler
	 */
	void setEnableClosureCompiler(Boolean enableClosureCompiler);

	Integer getFragmentCount();

	/**
	 * Sets the "-XfragmentCount" option.
	 * 
	 * @param fragmentCount
	 */
	void setFragmentCount(Integer fragmentCount);

	File getMissingDepsFile();

	/**
	 * @param missingDepsFile the missingDepsFile to set
	 */
	void setMissingDepsFile(File missingDepsFile);

	Namespace getNamespace();

	/**
	 * @param namespace the namespace to set
	 */
	void setNamespace(Namespace namespace);

	Boolean getEnforceStrictResources();

	/**
	 * @param enforceStrictResources the enforceStrictResources to set
	 */
	void setEnforceStrictResources(Boolean enforceStrictResources);

	Boolean getIncrementalCompileWarnings();

	/**
	 * @param incrementalCompileWarnings the incrementalCompileWarnings to set
	 */
	void setIncrementalCompileWarnings(Boolean incrementalCompileWarnings);

	Boolean getOverlappingSourceWarnings();

	/**
	 * @param overlappingSourceWarnings the overlappingSourceWarnings to set
	 */
	void setOverlappingSourceWarnings(Boolean overlappingSourceWarnings);

	Boolean getSaveSource();

	/**
	 * @param saveSource the saveSource to set
	 */
	void setSaveSource(Boolean saveSource);

	File getSaveSourceOutput();

	/**
	 * @param saveSourceOutput the saveSourceOutput to set
	 */
	void setSaveSourceOutput(File saveSourceOutput);

	List<Object> getJvmArgs();

	void setJvmArgs(Object... args);

}