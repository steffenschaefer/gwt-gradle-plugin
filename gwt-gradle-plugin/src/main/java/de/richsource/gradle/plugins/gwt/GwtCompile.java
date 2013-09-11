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

import java.util.concurrent.Callable;


public class GwtCompile extends AbstractGwtCompile {
	
	/** {@inheritDoc} */
	@Override
	protected boolean isDevTask() {
		return false;
	}
	
	protected void configure(final GwtCompileOptions options) {
		super.configure(options);
		
		conventionMapping("draftCompile", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDraftCompile();
			}
		});
		conventionMapping("compileReport", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getCompileReport();
			}
		});
		conventionMapping("compilerMetrics", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getCompilerMetrics();
			}
		});
		conventionMapping("validateOnly", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getValidateOnly();
			}
		});
		conventionMapping("disableGeneratingOnShards", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableGeneratingOnShards();
			}
		});
		conventionMapping("optimize", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getOptimize();
			}
		});
		conventionMapping("disableAggressiveOptimization", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableAggressiveOptimization();
			}
		});
		conventionMapping("disableClassMetadata", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableClassMetadata();
			}
		});
		conventionMapping("disableCastChecking", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableCastChecking();
			}
		});
		conventionMapping("ea", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getEa();
			}
		});
		conventionMapping("disableRunAsync", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableRunAsync();
			}
		});
		conventionMapping("style", new Callable<Style>() {
			@Override
			public Style call() throws Exception {
				return options.getStyle();
			}
		});
		conventionMapping("soycDetailed", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getSoycDetailed();
			}
		});
		conventionMapping("strict", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getStrict();
			}
		});
		conventionMapping("disableSoycHtml", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getDisableSoycHtml();
			}
		});
		conventionMapping("enableClosureCompiler", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getEnableClosureCompiler();
			}
		});
		conventionMapping("fragmentCount", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getFragmentCount();
			}
		});
	}
}
