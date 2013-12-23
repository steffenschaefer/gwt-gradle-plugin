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

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.gradle.api.Project;
import org.gradle.api.internal.project.AbstractProject;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.bundling.War;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.eclipse.model.BuildCommand;
import org.gradle.plugins.ide.eclipse.model.EclipseModel;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

public class GwtPluginTest {

	private Project project;
	private TaskContainer tasks;
	private ExtensionContainer extensions;

	@Before
	public void setUp() {
		project = ProjectBuilder.builder().build();
		project.getPlugins().apply(GwtPlugin.class);

		extensions = project.getExtensions();
		tasks = project.getTasks();
	}

	@Test
	public void testExtensionAvailable() {
		assertThat(extensions.getByName(GwtBasePlugin.EXTENSION_NAME), instanceOf(GwtPluginExtension.class));
	}
	
	@Test
	public void testConfigurationAvailable() {
		assertNotNull(project.getConfigurations().findByName(GwtBasePlugin.GWT_CONFIGURATION));
	}

	@Test
	public void testBasicTasksAvailable() {
		assertThat(tasks.getByName(GwtBasePlugin.TASK_COMPILE_GWT), instanceOf(GwtCompile.class));
		assertThat(tasks.getByName(GwtBasePlugin.TASK_DRAFT_COMPILE_GWT), instanceOf(GwtDraftCompile.class));
	}
	
	@Test
	public void testSuperDevTaskAvailable() {
		getExtension().setCodeserver(true);
		((AbstractProject)project).evaluate();
		
		assertThat(tasks.getByName(GwtBasePlugin.TASK_GWT_SUPER_DEV), instanceOf(GwtSuperDev.class));
	}
	
	@Test
	public void testSuperDevTaskNotAvailable() {
		getExtension().setCodeserver(false);
		((AbstractProject)project).evaluate();
		
		assertNull(tasks.findByName(GwtBasePlugin.TASK_GWT_SUPER_DEV));
	}
	
	@Test
	public void testWarTasksAvailable() {
		project.getPlugins().apply(WarPlugin.class);
		
		assertThat(tasks.getByName(GwtWarPlugin.TASK_WAR_TEMPLATE), instanceOf(Copy.class));
		assertThat(tasks.getByName(GwtWarPlugin.TASK_GWT_DEV), instanceOf(GwtDev.class));
		assertThat(tasks.getByName(GwtWarPlugin.TASK_DRAFT_WAR), instanceOf(War.class));
	}
	
	@Test
	public void testEclipseSetup() {
		project.getPlugins().apply(EclipsePlugin.class);
		
		final EclipseModel eclipseModel = project.getExtensions().getByType(EclipseModel.class);
		assertThat(eclipseModel.getProject().getNatures(), hasItem(GwtEclipsePlugin.ECLIPSE_NATURE));
		assertThat(eclipseModel.getProject().getBuildCommands(), hasItem(new BuildCommand(GwtEclipsePlugin.ECLIPSE_BUILDER_PROJECT_VALIDATOR)));
		assertThat(eclipseModel.getProject().getBuildCommands(), not(hasItem(new BuildCommand(GwtEclipsePlugin.ECLIPSE_BUILDER_WEBAPP_VALIDATOR))));
		
		project.getPlugins().apply(WarPlugin.class);
		assertThat(eclipseModel.getProject().getBuildCommands(), hasItem(new BuildCommand(GwtEclipsePlugin.ECLIPSE_BUILDER_WEBAPP_VALIDATOR)));
	}
	
	private GwtPluginExtension getExtension() {
		return extensions.getByType(GwtPluginExtension.class);
	}

}
