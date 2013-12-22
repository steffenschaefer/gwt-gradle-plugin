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

import java.io.File;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;

public class GwtEclipsePlugin implements Plugin<Project> {

	public static final String ECLIPSE_NATURE = "com.google.gwt.eclipse.core.gwtNature";
	public static final String ECLIPSE_BUILDER_PROJECT_VALIDATOR = "com.google.gwt.eclipse.core.gwtProjectValidator";
	public static final String ECLIPSE_BUILDER_WEBAPP_VALIDATOR = "com.google.gdt.eclipse.core.webAppProjectValidator";
	public static final String ECLIPSE_GWT_CONTAINER = "com.google.gwt.eclipse.core.GWT_CONTAINER";
	
	private static final Logger logger = Logging.getLogger(GwtEclipsePlugin.class);

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(EclipsePlugin.class);
		final GwtBasePlugin gwtBasePlugin = project.getPlugins().apply(GwtBasePlugin.class);
		
		final GwtPluginExtension extension = gwtBasePlugin.getExtension();
		
		
		final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
		logger.debug("Configuring eclipse model with basic GWT settings");
		eclipseModel.getProject().natures(ECLIPSE_NATURE);
		eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_PROJECT_VALIDATOR);
		
		project.getPlugins().withType(WarPlugin.class, new Action<WarPlugin>(){
			@Override
			public void execute(WarPlugin warPlugin) {
				logger.debug("Configuring eclipse model GWT web application settings");
				eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_WEBAPP_VALIDATOR);
				
				project.getTasks().getByName(EclipsePlugin.getECLIPSE_TASK_NAME()).dependsOn(GwtBasePlugin.TASK_WAR_TEMPLATE);
				project.getTasks().getByName(getAssociatedCleanTask(EclipsePlugin.getECLIPSE_TASK_NAME())).dependsOn(getAssociatedCleanTask(GwtBasePlugin.TASK_WAR_TEMPLATE));
				
				project.afterEvaluate(new Action<Project>() {
					@Override
					public void execute(final Project project) {
						final File devWar = extension.getDevWar();
						final File classes = new File(devWar, "WEB-INF/classes");
						eclipseModel.getClasspath().setDefaultOutputDir(classes);
					}
				});
			}
		});
		
		project.afterEvaluate(new Action<Project>() {
			@Override
			public void execute(final Project project) {
				final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
				eclipseModel.getClasspath().getPlusConfigurations().add(gwtBasePlugin.getGwtConfiguration());
				
				if(extension.getEclipse().isAddGwtContainer()) {
					logger.debug("Using GWT_CONTAINER for eclipse");
					eclipseModel.getClasspath().getContainers().add(ECLIPSE_GWT_CONTAINER);
					eclipseModel.getClasspath().getMinusConfigurations().add(gwtBasePlugin.getGwtSdkConfiguration());
				} else {
					logger.debug("Not using GWT_CONTAINER for eclipse");
					eclipseModel.getClasspath().getPlusConfigurations().add(gwtBasePlugin.getGwtSdkConfiguration());
				}
			}
		});
	}
	
	private String getAssociatedCleanTask(final String taskName) {
		final String first = taskName.substring(0, 1).toUpperCase();
		final String rest = taskName.substring(1);
		return "clean"+first+rest;
	}
}
