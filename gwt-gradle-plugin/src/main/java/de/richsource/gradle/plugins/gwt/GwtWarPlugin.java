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
import java.util.concurrent.Callable;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.bundling.War;

public class GwtWarPlugin implements Plugin<Project> {

	public static final String TASK_WAR_TEMPLATE = "warTemplate";
	public static final String TASK_DRAFT_WAR = "draftWar";
	public static final String TASK_GWT_DEV = "gwtDev";
	public static final String TASK_GWT_SUPER_DEV = "gwtSuperDev";

	private static final Logger logger = Logging.getLogger(GwtWarPlugin.class);

	@Override
	public void apply(final Project project) {
		project.getPlugins().apply(WarPlugin.class);
		final GwtBasePlugin gwtBasePlugin = project.getPlugins().apply(
				GwtBasePlugin.class);

		final GwtPluginExtension extension = gwtBasePlugin.getExtension();

		final GwtCompile compileTask = (GwtCompile) project.getTasks()
				.getByName(GwtBasePlugin.TASK_COMPILE_GWT);

		final GwtDraftCompile draftCompileTask = (GwtDraftCompile) project
				.getTasks().getByName(GwtBasePlugin.TASK_DRAFT_COMPILE_GWT);

		final War warTask = (War) project.getTasks().getByName(
				WarPlugin.WAR_TASK_NAME);
		
		logger.debug("Configuring war plugin with GWT settings");

		warTask.from(compileTask.getOutputs());
		
		final WarPluginConvention warPluginConvention = (WarPluginConvention) project.getConvention().getPlugins().get("war");

		final ExplodedWar warTemplateTask = project.getTasks().create(
				TASK_WAR_TEMPLATE, ExplodedWar.class);
		warTemplateTask.from(new Callable<File>(){
			@Override
			public File call() {
				return warPluginConvention.getWebAppDir();
			}});
		warTemplateTask.dependsOn(new Callable<FileCollection>() {
            public FileCollection call() throws Exception {
                return project.getConvention().getPlugin(JavaPluginConvention.class).getSourceSets().getByName(
                        SourceSet.MAIN_SOURCE_SET_NAME).getRuntimeClasspath();
            }
        });
		warTemplateTask.classpath(new Object[] {new Callable<FileCollection>() {
            public FileCollection call() throws Exception {
                return warTask.getClasspath();
            }
        }});
		warTemplateTask.conventionMapping("destinationDir", new Callable<File>() {
				@Override
				public File call() throws Exception {
					return extension.getDevWar();
				}
			});
		warTemplateTask
				.setDescription("Creates an exploded web application template to be used by GWT dev mode and eclipse to ensure src/main/webapp stays clean");

		final GwtDev devModeTask = project.getTasks().create(TASK_GWT_DEV,
				GwtDev.class);
		devModeTask.conventionMapping("war", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return extension.getDevWar();
			}
		});

		final War draftWar = project.getTasks().create(TASK_DRAFT_WAR,
				War.class);
		draftWar.from(draftCompileTask.getOutputs());

		draftWar.setBaseName(warTask.getBaseName() + "-draft");
		draftWar.setDescription("Creates a war using the output of the task "
				+ GwtBasePlugin.TASK_DRAFT_COMPILE_GWT);

		for (Object dependsTask : warTask.getDependsOn()) {
			devModeTask.dependsOn(dependsTask);
		}
		devModeTask.dependsOn(warTemplateTask);

	}

}
