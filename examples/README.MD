# Example project for the GWT Gradle Plugin

Examples for different use-cases can be found in several subdirectories.

* simple-war: This example shows how to configure a simple GWT web application project.
* library: The library example shows how to configure a library project that is not set up as web application project. This example uses the dependency management and basic eclipse setup.
* war-using-library: This example shows how to configure a web application project that references the library project.
* super-dev-mode: This example is set up with support of Super Dev Mode. To test this, run "gradle jettyDraftWar" in one shell and "gradle gwtSuperDev" in another shell.
* testing: This shows the minimum setup to run GWTTestCases using the gradle "test" task.
