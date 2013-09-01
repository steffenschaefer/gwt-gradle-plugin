package de.richsource.gradle.plugins.gwt;

import org.gradle.api.DefaultTask;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class GwtCompileTask extends DefaultTask {
	
	@Input AbstractGwtConfiguration config;
	
	@TaskAction
	public void run() {
		
	}
}
