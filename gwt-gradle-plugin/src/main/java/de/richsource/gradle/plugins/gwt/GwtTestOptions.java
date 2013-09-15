package de.richsource.gradle.plugins.gwt;

public class GwtTestOptions {
	private boolean hasGwtTests = false;
	private boolean prod = false;

	public boolean isHasGwtTests() {
		return hasGwtTests;
	}

	public void setHasGwtTests(boolean hasGwtTests) {
		this.hasGwtTests = hasGwtTests;
	}

	public boolean isProd() {
		return prod;
	}

	public void setProd(boolean prod) {
		this.prod = prod;
	}
}
