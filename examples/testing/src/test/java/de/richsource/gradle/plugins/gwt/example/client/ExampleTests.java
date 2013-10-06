package de.richsource.gradle.plugins.gwt.example.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.junit.client.GWTTestCase;


public class ExampleTests extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "de.richsource.gradle.plugins.gwt.example.Test";
	}
	
	public void testSomething() {
		assertNotNull(Document.get().getBody());
	}

}
