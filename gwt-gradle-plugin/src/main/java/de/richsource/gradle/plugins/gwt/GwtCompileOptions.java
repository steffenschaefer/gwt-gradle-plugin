/**
 * Copyright (C) 2013 Steffen Schaefer
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


public interface GwtCompileOptions {

	Integer getLocalWorkers();

	void setLocalWorkers(Integer localWorkers);

	Boolean getDraftCompile();

	void setDraftCompile(Boolean draftCompile);

	Boolean getCompileReport();

	void setCompileReport(Boolean compileReport);

	Boolean getCompilerMetrics();

	void setCompilerMetrics(Boolean compilerMetrics);

	Boolean getValidateOnly();

	void setValidateOnly(Boolean validateOnly);

	Boolean getDisableGeneratingOnShards();

	void setDisableGeneratingOnShards(Boolean disableGeneratingOnShards);

	Integer getOptimize();

	void setOptimize(Integer optimize);

	Boolean getDisableAggressiveOptimization();

	void setDisableAggressiveOptimization(Boolean disableAggressiveOptimization);

	Boolean getDisableClassMetadata();

	void setDisableClassMetadata(Boolean disableClassMetadata);

	Boolean getDisableCastChecking();

	void setDisableCastChecking(Boolean disableCastChecking);

	Boolean getEa();

	void setEa(Boolean ea);

	Boolean getDisableRunAsync();

	void setDisableRunAsync(Boolean disableRunAsync);

	Style getStyle();

	void setStyle(Style style);

	Boolean getSoycDetailed();

	void setSoycDetailed(Boolean soycDetailed);

	Boolean getStrict();

	void setStrict(Boolean strict);

	Boolean getDisableSoycHtml();

	void setDisableSoycHtml(Boolean disableSoycHtml);

	Boolean getEnableClosureCompiler();

	void setEnableClosureCompiler(Boolean enableClosureCompiler);

	Integer getFragmentCount();

	void setFragmentCount(Integer fragmentCount);

}