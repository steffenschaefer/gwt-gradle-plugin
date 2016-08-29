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
package de.richsource.gradle.plugins.gwt.eclipse.internal;

import java.io.File;

import de.richsource.gradle.plugins.gwt.eclipse.GdtOptions;

public class GdtOptionsImpl implements GdtOptions {
	private File lastWarOutDir;
	private File warSrcDir;
	private Boolean warSrcDirIsOutput;

	/** {@inheritDoc} */
	@Override
	public File getLastWarOutDir() {
		return lastWarOutDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setLastWarOutDir(File lastWarOutDir) {
		this.lastWarOutDir = lastWarOutDir;
	}

	/** {@inheritDoc} */
	@Override
	public File getWarSrcDir() {
		return warSrcDir;
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDir(File warSrcDir) {
		this.warSrcDir = warSrcDir;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getWarSrcDirIsOutput() {
		return warSrcDirIsOutput;
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDirIsOutput(Boolean warSrcDirIsOutput) {
		this.warSrcDirIsOutput = warSrcDirIsOutput;
	}

}
