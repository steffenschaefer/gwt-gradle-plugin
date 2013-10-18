# Example project for the GWT Gradle Plugin

This directory contans several example projects to demonstrate various aspects of the gwt-gradle-plugin.

To be able to build these projects, you need to run "gradle publish" in the root directory of this git repository. This will build the plugin to a local Maven repository (which is referenced by the example projects).

The following example projects are currently available:

* simple-war: This example shows how to configure a simple GWT web application project.
* library: The library example shows how to configure a library project that is not set up as web application project. This example uses the dependency management and basic eclipse setup.
* war-using-library: This example shows how to configure a web application project that references the library project.
* super-dev-mode: This example is set up with support of Super Dev Mode. To test this, run "gradle jettyDraftWar" in one shell and "gradle gwtSuperDev" in another shell.
* testing: This shows the minimum setup to run GWTTestCases using the gradle "test" task.
