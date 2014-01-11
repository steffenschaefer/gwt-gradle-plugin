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
package de.richsource.gradle.plugins.gwt.eclipse;

import java.io.File;

/**
 * Options to be written to the Eclipse GDT settings file.
 */
public interface GdtOptions {

	/**
	 * Sets the value of the warSrcDirIsOutput property. This specifies if the warSrcDir is the one to use to compile to or use for Dev Mode.
	 * 
	 * @param warSrcDirIsOutput
	 */
	void setWarSrcDirIsOutput(Boolean warSrcDirIsOutput);

	Boolean getWarSrcDirIsOutput();

	/**
	 * Sets the location of the web application source (typical location is src/main/webapp). 
	 * 
	 * @param warSrcDir
	 */
	void setWarSrcDir(File warSrcDir);

	File getWarSrcDir();

	/**
	 * If set this will cause the lastWarOutDir property to be set to the specified path.
	 * 
	 * @param lastWarOutDir
	 */
	void setLastWarOutDir(File lastWarOutDir);

	File getLastWarOutDir();

}
