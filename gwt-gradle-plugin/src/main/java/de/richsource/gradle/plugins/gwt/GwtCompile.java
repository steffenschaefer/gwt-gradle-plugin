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
import java.util.concurrent.Callable;

import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;

/**
 * Task to run the GWT compiler for production quality output.
 */
public class GwtCompile extends AbstractGwtCompile {
	
	/** {@inheritDoc} */
	@Override
	protected boolean isDevTask() {
		return false;
	}
	
	protected void configure(final GwtCompileOptions options) {
		super.configure(options);
		
		ConventionMapping conventionMapping =((IConventionAware)this).getConventionMapping();
		conventionMapping.map("draftCompile", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDraftCompile();
			}
		});
		conventionMapping.map("compileReport", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getCompileReport();
			}
		});
		conventionMapping.map("compilerMetrics", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getCompilerMetrics();
			}
		});
		conventionMapping.map("validateOnly", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getValidateOnly();
			}
		});
		conventionMapping.map("disableGeneratingOnShards", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableGeneratingOnShards();
			}
		});
		conventionMapping.map("optimize", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getOptimize();
			}
		});
		conventionMapping.map("disableAggressiveOptimization", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableAggressiveOptimization();
			}
		});
		conventionMapping.map("disableClassMetadata", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableClassMetadata();
			}
		});
		conventionMapping.map("disableCastChecking", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableCastChecking();
			}
		});
		conventionMapping.map("ea", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getEa();
			}
		});
		conventionMapping.map("disableRunAsync", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableRunAsync();
			}
		});
		conventionMapping.map("style", new Callable<Style>() {
			@Override
			public Style call() throws Exception {
				return options.getStyle();
			}
		});
		conventionMapping.map("soycDetailed", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getSoycDetailed();
			}
		});
		conventionMapping.map("strict", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getStrict();
			}
		});
		conventionMapping.map("disableSoycHtml", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableSoycHtml();
			}
		});
		conventionMapping.map("enableClosureCompiler", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getEnableClosureCompiler();
			}
		});
		conventionMapping.map("fragmentCount", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getFragmentCount();
			}
		});
		conventionMapping.map("missingDepsFile", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return options.getMissingDepsFile();
			}
		});
		conventionMapping.map("namespace", new Callable<Namespace>() {
			@Override
			public Namespace call() throws Exception {
				return options.getNamespace();
			}
		});
		conventionMapping.map("enforceStrictResources", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getEnforceStrictResources();
			}
		});
		conventionMapping.map("incrementalCompileWarnings", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getIncrementalCompileWarnings();
			}
		});
		conventionMapping.map("overlappingSourceWarnings", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getOverlappingSourceWarnings();
			}
		});
		conventionMapping.map("saveSource", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getSaveSource();
			}
		});
		conventionMapping.map("saveSourceOutput", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return options.getSaveSourceOutput();
			}
		});
	}
}
