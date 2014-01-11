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

/**
 * Eclipse related options used by the {@link GwtEclipsePlugin}.
 */
public class GwtEclipseOptions {
	
	private boolean addGwtContainer = true;

	public boolean isAddGwtContainer() {
		return addGwtContainer;
	}

	/**
	 * Sets if the {@code com.google.gwt.eclipse.core.GWT_CONTAINER} should be added to the eclipse classpath instead of using the Gradle dependencies.
	 * 
	 * @param addGwtContainer true to use the GWT_CONTAINER, false to use the dependencies defined by Gradle
	 */
	public void setAddGwtContainer(boolean addGwtContainer) {
		this.addGwtContainer = addGwtContainer;
	}
}
