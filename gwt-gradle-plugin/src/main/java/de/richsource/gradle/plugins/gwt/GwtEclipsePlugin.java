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

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.ExtensionAware;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;

import de.richsource.gradle.plugins.gwt.eclipse.GdtOptions;
import de.richsource.gradle.plugins.gwt.eclipse.GenerateGdt;
import de.richsource.gradle.plugins.gwt.eclipse.internal.GdtOptionsImpl;

// TODO choose different name as this is no real plugin
public class GwtEclipsePlugin {

	public static final String ECLIPSE_NATURE = "com.google.gwt.eclipse.core.gwtNature";
	public static final String ECLIPSE_BUILDER_PROJECT_VALIDATOR = "com.google.gwt.eclipse.core.gwtProjectValidator";
	public static final String ECLIPSE_BUILDER_WEBAPP_VALIDATOR = "com.google.gdt.eclipse.core.webAppProjectValidator";
	public static final String ECLIPSE_GWT_CONTAINER = "com.google.gwt.eclipse.core.GWT_CONTAINER";
	public static final String GENERATE_GDT_TASK = "generateGdt";
	
	private static final Logger logger = Logging.getLogger(GwtEclipsePlugin.class);
	private Project project;

	public void apply(final Project project, final GwtBasePlugin gwtBasePlugin) {
		this.project = project;
		project.getPlugins().apply(EclipsePlugin.class);
		
		final GwtPluginExtension extension = gwtBasePlugin.getExtension();
		final GwtEclipseOptions eclipseExtension = ((ExtensionAware)extension).getExtensions().create("eclipse", GwtEclipseOptions.class);
		
		final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
		logger.debug("Configuring eclipse model with basic GWT settings");
		eclipseModel.getProject().natures(ECLIPSE_NATURE);
		eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_PROJECT_VALIDATOR);
		
		project.getPlugins().withType(GwtWarPlugin.class, new Action<GwtWarPlugin>(){
			@Override
			public void execute(GwtWarPlugin warPlugin) {
				logger.debug("Configuring eclipse model GWT web application settings");

				eclipseModel.getProject().buildCommand(ECLIPSE_BUILDER_WEBAPP_VALIDATOR);
				
				project.getTasks().getByName(EclipsePlugin.ECLIPSE_TASK_NAME).dependsOn(GwtWarPlugin.TASK_WAR_TEMPLATE);
				project.getTasks().getByName(getAssociatedCleanTask(EclipsePlugin.ECLIPSE_TASK_NAME)).dependsOn(getAssociatedCleanTask(GwtWarPlugin.TASK_WAR_TEMPLATE));
				
				final GdtOptions gdtExtension = ((ExtensionAware)eclipseExtension).getExtensions().create("gdt", GdtOptionsImpl.class);
				configureGdtExtension(extension, gdtExtension);
				
				configureGenerateGdt(gdtExtension);
				
				GenerateGdt generateGdt = project.getTasks().create(GENERATE_GDT_TASK, GenerateGdt.class);
				generateGdt.setSettingsFile(project.file(".settings/com.google.gdt.eclipse.core.prefs"));
				project.getTasks().getByName(EclipsePlugin.ECLIPSE_TASK_NAME).dependsOn(generateGdt);
				
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
				
				if(eclipseExtension.isAddGwtContainer()) {
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

	private void configureGdtExtension(final GwtPluginExtension extension,
			final GdtOptions gdtExtension) {
		final WarPluginConvention warPluginConvention = (WarPluginConvention) project.getConvention().getPlugins().get("war");
		ConventionMapping conventionMapping = ((IConventionAware)gdtExtension).getConventionMapping();
		conventionMapping.map("warSrcDir", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return warPluginConvention.getWebAppDir();
			}
		});
		conventionMapping.map("lastWarOutDir", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return extension.getDevWar();
			}
		});
		gdtExtension.setWarSrcDirIsOutput(false);
	}
	
	private void configureGenerateGdt(final GdtOptions gdtExtension) {
		project.getTasks().withType(GenerateGdt.class, new Action<GenerateGdt>() {

			@Override
			public void execute(GenerateGdt task) {
				ConventionMapping conventionMapping = ((IConventionAware)task).getConventionMapping();
				conventionMapping.map("warSrcDir", new Callable<File>() {
					@Override
					public File call() throws Exception {
						return gdtExtension.getWarSrcDir();
					}
				});
				conventionMapping.map("warSrcDirIsOutput", new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return gdtExtension.getWarSrcDirIsOutput();
					}
				});
				conventionMapping.map("lastWarOutDir", new Callable<File>() {
					@Override
					public File call() throws Exception {
						return gdtExtension.getLastWarOutDir();
					}
				});
			}
		});
	}
	
	private String getAssociatedCleanTask(final String taskName) {
		final String first = taskName.substring(0, 1).toUpperCase();
		final String rest = taskName.substring(1);
		return "clean"+first+rest;
	}
}
