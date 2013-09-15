package de.richsource.gradle.plugins.gwt.example.client;

import org.junit.Assert;

import com.google.gwt.dom.client.Document;
import com.google.gwt.junit.client.GWTTestCase;

public class ExampleTests extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "de.richsource.gradle.plugins.gwt.example.Test";
	}
	
	public void testSomething() throws InterruptedException {
		System.out.println("foo");
		Thread.sleep(5000);
		Assert.assertNotNull(Document.get().getBody());
	}

}
