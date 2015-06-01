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

# Usage

The following example shows the code to set up gwt-gradle-plugin for a GWT web application project using Maven/Gradle standard layout.

    buildscript {
        repositories {
            jcenter()
            // Alternatively use:
    //        maven {
	//            url 'http://dl.bintray.com/steffenschaefer/maven'
	//        }
        }
        dependencies {
            classpath 'de.richsource.gradle.plugins:gwt-gradle-plugin:0.6'
        }
    }

    apply plugin: 'war'
    apply plugin: 'gwt'

    gwt {
        gwtVersion='2.7.0'
        modules '<YOUR-GWT-MODULE>'
    }
    
This will configure your GWT web project to execute the GWT compiler and include the compiler output into your *.war file. The code shown above also configures all GWT core dependencies (gwt-dev, gwt-user, gwt-servlet, ...).

To build the *.war file including your compiled GWT modules, simply call "gradle build".
If you want to start the GWT development mode simply call "gradle gwtDev".

To learn about different scenarios or more specific configuration needs, please refer to the [Documentation][doc]

[doc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/
[javadoc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/javadoc/
