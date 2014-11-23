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

import org.gradle.api.Task;
import org.gradle.api.specs.Spec;

/**
 * Task to run the GWT compiler for validation only so that no JavaScript output is produced.
 */
public class GwtCheck extends AbstractGwtCompile {
	public GwtCheck() {
		setValidateOnly(true);
		
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}
	
	/** {@inheritDoc} */
	@Override
	protected boolean isDevTask() {
		return false;
	}
}
