/**
 * Copyright (C) 2013-2016 Steffen Schaefer
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
package de.richsource.gradle.plugins.gwt.internal;

import org.gradle.api.Action;

import groovy.lang.Closure;

public class ActionClosure<T> extends Closure<T> {

	private static final long serialVersionUID = -8589397880380161976L;
	
	private Action<T> action;

	public ActionClosure(Object owner, Action<T> action) {
		super(owner);
		this.action = action;
	}

	public void doCall(T object) {
		action.execute(object);
	}
}
