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

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;
import org.gradle.plugins.ide.idea.IdeaPlugin;
import org.gradle.plugins.ide.idea.model.IdeaModel;

import java.util.Collection;

/**
 * This "plugin" improves the IntelliJ IDEA integration by adding
 * the gwt dependencies to the project.
 *
 * idea {
 *     module {
 *         scopes.PROVIDED.plus  += configurations.gwtSdk
 *         scopes.PROVIDED.plus  += configurations.gwt
 *     }
 * }
 */
public class GwtIdeaPlugin {

	private static final String SCOPE_PROVIDED = "PROVIDED";
	private static final String KEY_PLUS = "plus";

	public void apply(final Project project, final GwtBasePlugin gwtBasePlugin) {
		project.getPlugins().apply(IdeaPlugin.class);

		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(final Project project) {
				IdeaModel ideaModel = project.getExtensions().getByType(IdeaModel.class);
				Collection<Configuration> configurations =
						ideaModel.getModule().getScopes().get(SCOPE_PROVIDED).get(KEY_PLUS);

				Configuration gwtSdkConfiguration = gwtBasePlugin.getGwtSdkConfiguration();
				Configuration gwtConfiguration = gwtBasePlugin.getGwtConfiguration();

				configurations.add(gwtSdkConfiguration);
				configurations.add(gwtConfiguration);
			}
		});
	}
}
