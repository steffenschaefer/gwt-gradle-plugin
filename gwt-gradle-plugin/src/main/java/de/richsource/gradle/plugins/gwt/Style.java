package de.richsource.gradle.plugins.gwt;

/**
 * Output styles suported by the GWT compiler.
 */
public enum Style {
	/** Obfuscated or minimized style. This should be used for production. */
	OBF,
	/** Human readable output. */
	PRETTY,
	/** Detailed and human readable output. */
	DETAILED
}
