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
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.testing.Test;

public class GwtTestPlugin implements Plugin<Project> {
	
	private static final Logger logger = Logging.getLogger(GwtTestPlugin.class);
	private Project project;

	@Override
	public void apply(final Project project) {
		this.project = project;
		final GwtBasePlugin gwtBasePlugin = project.getPlugins().apply(GwtBasePlugin.class);
		
		final SourceSet testSourceSet = getTestSourceSet();
		testSourceSet.setCompileClasspath(testSourceSet.getCompileClasspath().plus(gwtBasePlugin.getAllGwtConfigurations()));
		testSourceSet.setRuntimeClasspath(
				project.files(
						getMainSourceSet().getAllJava().getSrcDirs().toArray())
						.plus(project.files(testSourceSet.getAllJava()
								.getSrcDirs().toArray()))
				.plus(gwtBasePlugin.getAllGwtConfigurations()).plus(testSourceSet
				.getRuntimeClasspath()));
		
		project.getTasks().withType(Test.class, new Action<Test>() {
			@Override
			public void execute(final Test testTask) {
				testTask.getTestLogging().setShowStandardStreams(true);
				
				final GwtTestExtension testExtension = testTask.getExtensions().create("gwt", GwtTestExtension.class);
				testExtension.configure(gwtBasePlugin.getExtension(), (IConventionAware) testExtension);

				testTask.doFirst(new Action<Task>() {
					@Override
					public void execute(Task arg0) {
						String gwtArgs = testExtension.getParameterString();
						testTask.systemProperty("gwt.args", gwtArgs);
						logger.info("Using gwt.args for test: "+ gwtArgs);

						if (testExtension.getCacheDir() != null) {
							testTask.systemProperty("gwt.persistentunitcachedir", testExtension.getCacheDir());
							testExtension.getCacheDir().mkdirs();
							logger.info("Using gwt.persistentunitcachedir for test: {0}", testExtension.getCacheDir());
						}
					}
				});
			}
		});
	}

	
	private SourceSet getMainSourceSet() {
		return getJavaConvention().getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME);
	}
	
	private SourceSet getTestSourceSet() {
		return getJavaConvention().getSourceSets().getByName(SourceSet.TEST_SOURCE_SET_NAME);
	}

	private JavaPluginConvention getJavaConvention() {
		return project.getConvention().getPlugin(JavaPluginConvention.class);
	}
}
