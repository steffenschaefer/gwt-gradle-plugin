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


/**
 * GWT test related options.
 */
public class GwtTestOptions extends GwtTestOptionsBase {
	private boolean hasGwtTests = true;

	public boolean isHasGwtTests() {
		return hasGwtTests;
	}

	/**
	 * If set to true this causes the test task to be manipulated to be able to run GWTTestCases.
	 * 
	 * @param hasGwtTests true if the project has GWTTestCases, false otherwise.
	 */
	public void setHasGwtTests(boolean hasGwtTests) {
		this.hasGwtTests = hasGwtTests;
	}
}
