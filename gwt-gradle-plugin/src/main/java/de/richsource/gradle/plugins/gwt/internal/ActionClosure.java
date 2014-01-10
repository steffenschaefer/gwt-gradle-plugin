package de.richsource.gradle.plugins.gwt.internal;

import org.gradle.api.Action;

import groovy.lang.Closure;

public class ActionClosure<T> extends Closure<T> {

	private static final long serialVersionUID = -8589397880380161976L;
	
	private Action<T> action;

	public ActionClosure(Object owner, Action<T> action) {
		super(owner);
		this.action = action;
	}

	public void doCall(T object) {
		action.execute(object);
	}
}
