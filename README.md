# GWT Gradle Plugin

This plugin makes it easy to build projects using [GWT](http://www.gwtproject.org/). It provides several tasks to support the development and configures several aspects of your project to work with GWT automatically.

Among other things, the plugin provides these features:

* Running the GWT compiler and automatic inclusion of the compiled stuff in your *.war file
* Starting GWT Dev Mode
* Support for Super Dev Mode (GWT 2.5+)
* Configuration of different GWT modules for development and production
* GWT specific configuration of Eclipse projects

# Resources

* [Version history](http://steffenschaefer.github.io/gwt-gradle-plugin/versions.html)
* [Documentation][doc]
* [JavaDocs][javadoc]

# Examples

Several example projects can be found in [/examples](examples).

# Quickstart

This will guide you through the steps needed to set up gwt-gradle-plugin for a GWT web application project using Maven/Gradle standard layout.
To learn about different scenarios or more specific configuration needs, please refer to the [Documentation][doc]

## Plugin dependency

As this is not a core Gradle plugin, you have to ensure, that Gradle knows how to get the plugin. Do do this, add the following lines to your build.gradle:

    buildscript {
        repositories {
            maven {
                url 'https://github.com/steffenschaefer/gwt-gradle-plugin/raw/maven-repo/'
            }
            mavenCentral()
        }
        dependencies {
            classpath 'de.richsource.gradle.plugins:gwt-gradle-plugin:0.3'
        }
    }

## Basic web application setup

Supposed you already applied the gradle "war" plugin to your project, you have to also apply the "gwt" plugin:

    apply plugin: 'war'
    apply plugin: 'gwt'

Now you have to configure the GWT modules to compile into your web application:

    gwt {
        modules '<YOUR-GWT-MODULE>'
    }
    
If you want to use automatically configured GWT dependencies (gwt-dev, gwt-user, ...) you have to extend the configuration to set the desired GWT version:

    gwt {
        gwtVersion='2.5.1'
        modules '<YOUR-GWT-MODULE>'
    }

[doc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/
[javadoc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/javadoc/
