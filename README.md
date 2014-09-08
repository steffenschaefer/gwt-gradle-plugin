# GWT Gradle Plugin

This plugin makes it easy to build projects using [GWT](http://www.gwtproject.org/). It provides several tasks to support the development and configures several aspects of your project to work with GWT automatically.

Among other things, the plugin provides these features:

* Running the GWT compiler and automatic inclusion of the compiled stuff in your *.war file
* Starting GWT Dev Mode
* Support for Super Dev Mode (GWT 2.5+)
* Configuration of different GWT modules for development and production
* GWT specific configuration of Eclipse projects

# Note about contributions/pull requests

I really like how open source development works including contributions, but currently I will not accept any pull request. It's not that your contributions aren't good, but it makes things a little bit more complicated for me...

Let me explain: I personnally would like to see some kind of official support for Gradle very close to the GWT project. I think most developers will agree with this. I would like to contribute the whole code to the GWT project as base for an official plugin. I don't know if the steering comitee or somebody else who is responsible for such things will accept this, but things will be very complicated if people contributed code who potentially haven't signed the CLA (or even worse people who can't sign it due to legal issues).

But how can you help?
You can help by filing bugs for things that don't work or things you would like to see better support or easier solutions for.

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
            maven {
                url 'https://github.com/steffenschaefer/gwt-gradle-plugin/raw/maven-repo/'
            }
            mavenCentral()
        }
        dependencies {
            classpath 'de.richsource.gradle.plugins:gwt-gradle-plugin:0.4'
        }
    }

    apply plugin: 'war'
    apply plugin: 'gwt'

    gwt {
        gwtVersion='2.6.1'
        modules '<YOUR-GWT-MODULE>'
    }
    
This will configure your GWT web project to execute the GWT compiler and include the compiler output into your *.war file. The code shown above also configures all GWT core dependencies (gwt-dev, gwt-user, gwt-servlet, ...).

To build the *.war file including your compiled GWT modules, simply call "gradle build".
If you want to start the GWT development mode simply call "gradle gwtDev".

To learn about different scenarios or more specific configuration needs, please refer to the [Documentation][doc]

[doc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/
[javadoc]: http://steffenschaefer.github.io/gwt-gradle-plugin/doc/latest/javadoc/
