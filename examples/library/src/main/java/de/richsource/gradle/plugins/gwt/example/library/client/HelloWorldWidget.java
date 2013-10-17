package de.richsource.gradle.plugins.gwt.example.library.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class HelloWorldWidget extends Widget {

	public HelloWorldWidget() {
		setElement(Document.get().createDivElement());
		getElement().setInnerText("Hello GWT World!");
	}
}
