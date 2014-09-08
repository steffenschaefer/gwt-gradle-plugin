---
layout : default
title :  "Plugin design"
---

## Plugin Design

The plugin is designed to work with the following principles in mind (but can be configured to work different):

* The webapp source (typically /src/main/webapp) is holy and won't be polluted with generated stuff. Instead a special working dir is set up with an exploded template of the webapp (typically /war)
* Compilation is done with a dedicated output folder to make incremental builds work
* All output is configured to be written to “${buildDir}/gwt” with several sub directories for different purposes (look at the chapter “Default directory structure” for details)

## What does the plugin do with your project

This chapter describes what the plugin adds to your project and which default settings are changed.

### Default directory structure

Using the default configuration, the plugin will use the following directory structures:

* project root
    * src/main/webapp : default webapp directory configurable by “war” plugin
    * build : build dir configurable in gradle
        * gwt
            * out : output dir of the compileGwt task
            * draftOut : output dir of the draftCompileGwt task
            * extra : extra dir used by the GWT compiler / dev mode (e.g. compile report)
            * gen : output dir for generated files
            * work : work directory for several GWT tasks
            * cache : unit cache used by the GWT compiler / dev mode
            * log : log dir for the dev mode
    * war : webapp template to be used by GWT dev mode and eclipse

### Plugin extension

An extension with name “gwt” of type [de.richsource.gradle.plugins.gwt.GwtPluginExtension](javadoc/de/richsource/gradle/plugins/gwt/GwtPluginExtension.html). For details on how to configure the plugin, please refer to the chapter “Plugin configuration”. Alternatively you can have a look at the source code of this class to check what can be configured.

### Configuration

A configuration named “gwt” is added. This configuration should be used for all dependencies that are only needed by GWT itself (e.g. gwt-dev.jar). Dependencies associated with this configuration with be available at compile time but not at runtime.

### Basic plugin tasks

The plugin at least configures the following tasks:

* compileGwt: compiles the Java source code to JavaScript.
* draftCompileGwt: compiles the Java source code to JavaScript but without optimizations. This is usefull for development due to very small compilation times.
* gwtSuperDev: This runs the GWT codeserver used for Super Dev Mode (available with GWT 2.5+ only)

### GWT with “war” plugin

If your project uses the “gwt” plugin together with the “war” plugin the following changes will happen:

* The result of the “compileGwt” task is automatically added to the war file produced by the “war” task (the task is automatically executed if it’s output isn’t up to date).
* A task “warTemplate” is added which creates an exploded version of the webapp.
* A task “gwtDev” is added that runs the GWT Development Mode from the exploded webapp produced by “warTemplate”
* A Task “draftWar” is added that produces a war file similar to the on produced by the “war” task. The difference is that this war file will contain the output of the “draftCompileGwt” which leads to faster build times during development.

### GWT with “eclipse” plugin

If your project uses the “gwt” plugin together with the “eclipse” plugin the following changes will happen to your eclipse configuration:

* The nature "com.google.gwt.eclipse.core.gwtNature" is added. This nature is provided by the “Google Plugin for Eclipse”. It is assumed that you have installed this plugin when working with GWT and Eclipse.
* The build command "com.google.gwt.eclipse.core.gwtProjectValidator" is added. This nature is provided by the “Google Plugin for Eclipse”. It is assumed that you have installed this plugin when working with GWT and Eclipse.

### GWT with “eclipse” and “war” plugins

If your project uses the “gwt” plugin together with the “eclipse” and “war” plugins the following changes will happen to your eclipse configuration (additionally to the ones described above):

* The build command "com.google.gwt.eclipse.core.gwtProjectValidator" is added. 
* The default output directory is set to “/war/WEB-INF/classes". This makes it easy to run the development mode using the “Google Plugin for Eclipse”.
