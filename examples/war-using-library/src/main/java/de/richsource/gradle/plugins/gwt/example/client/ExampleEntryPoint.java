package de.richsource.gradle.plugins.gwt.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.richsource.gradle.plugins.gwt.example.library.client.HelloWorldWidget;

public class ExampleEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new HelloWorldWidget());
	}

}
