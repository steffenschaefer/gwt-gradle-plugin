---
layout : default
title :  "GWT Gradle Plugin"
---

## Compatibility

The plugin supports GWT 2.x only (GWT 1.x isn’t supported at all). Main testing was done with GWT 2.5 but it should work with older versions too.
The plugin is developed with Gradle 1.7 but should also work with Gradle 1.6. Due to used APIs it definitely won’t work with Gradle versions before 1.6.

## Plugin Design

The plugin is designed to work with the following principles in mind (but can be configured to work different):
* The webapp source (typically /src/main/webapp) is holy and won't be polluted with generated stuff. Instead a special working dir is set up with an exploded template of the webapp (typically /war)
* Compilation is done with a dedicated output folder to make incremental builds work
* All output is configured to be written to “${buildDir}/gwt” with several sub directories for different purposes (look at the chapter “Default directory structure” for details)

## Where to get the plugin?

Plugin binaries can be found in a Maven repository hosted at GitHub (URL: https://github.com/steffenschaefer/gwt-gradle-plugin/raw/maven-repo/). You can browse this repository [here](https://github.com/steffenschaefer/gwt-gradle-plugin/tree/maven-repo).

To build a version on your own, simply run "gradle publish" in the root directory of the code repository. After the build finished, a directory named "repo" will appear. This directory is a maven repository containing the plugin binaries. You can copy this directory to your project and use it as local repository.

## How to use the plugin?

### Plugin dependency

To configure your project to apply the plugin using the maven repository hosted at GitHub, you need the following:

{% highlight groovy linenos=table %}
buildscript {
    repositories {
        maven {
            url 'https://github.com/steffenschaefer/gwt-gradle-plugin/raw/maven-repo/'
        }
        mavenCentral()
    }
    dependencies {
        classpath 'de.richsource.gradle.plugins:gwt-gradle-plugin:0.1'
    }
}
{% endhighlight %}

### Basic web application setup

Supposed you already applied the gradle "war" plugin to your project, you have to also apply the "gwt" plugin:

{% highlight groovy linenos=table %}
apply plugin: 'war'
apply plugin: 'gwt'
{% endhighlight %}

Now you have to configure the GWT modules to compile into your web application:

{% highlight groovy linenos=table %}
gwt {
    modules '<YOUR-GWT-MODULE>'
}
{% endhighlight %}
    
If you want to use automatically configured GWT dependencies (gwt-dev, gwt-user, ...) you have to extend the configuration to set the desired GWT version:

{% highlight groovy linenos=table %}
gwt {
    gwtVersion='2.5.1'
    modules '<YOUR-GWT-MODULE>'
}
{% endhighlight %}

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

## How to debug Development Mode?

When running the task “gwtDev” you can specify a system property “-DgwtDev.debug=true” to enable debugging. This causes the build to stop when starting development mode and waiting for a debuger to attach to port 5005.
Now you can configure your IDE to connect to that debug port.

## How to run Super Dev Mode?

Be aware that the Super Dev Mode is ony available GWT 2.5+.
Additionally be aware that Super Dev Mode requires specific browsers to work!

This chapter does not describe the usage of “Super Dev Mode” in detail. Please refer to the official documentation to learn how to use it. Be aware that you need to adjust your *gwt.xml” file or provide a special one for development.

In contrast to the “normal” Development Mode, the Super Dev Mode does not start the complete web application for you. Instead you have to run the web application by yourselves. Please refer to the chapter “Running the draft war” for details on how to startup the draft war using a servlet container.

To start the Super Dev Mode, execute the task “gwtSuperDev”. When it is ready you will see the message “Next, visit: http://localhost:9876/”. Do exactly that and open “http://localhost:9876/” in your browser. You will now see two Buttons “Dev Mode On” and “Dev Mode Off”. Drag&drop these to your browser’s bookmark bar.

Now navigate your browser to the webapp hosting your application. Press the “Dev Mode On” bookmark. Now you should see a dialog listing all your gwt modules. Select the one you want to debug. The page will reload after the Super Dev Mode recompiled your GWT module. You can now debug the application using the developer tools of your browser.

## Plugin configuration

The following chapters describe usual use-cases and how do adjust the configuration accordingly.

### Automatic dependecy management

The plugin can automatically add the needed GWT dependencies for you. Everything you have to do is:
* Provide a repository where the GWT artifacts are hosted.
    If you haven’t already configured a repository and you want to use the Maven central repositroy, add the following to your build.gradle file:
    repositories { mavenCentral() }
* Define the GWT version. To do that, insert the following configuration to your “build.gradle” file:
    gwt { gwtVersion='2.5.1' }

### Activate compiler optimizations

The plugin applies the default optimization settings but that can be adjusted. The following example shows how to adjust some of the flags (please refer to the official documentation on what these flags do):

{% highlight groovy linenos=table %}
gwt {
    compiler {
        enableClosureCompiler = true; // activates -XenableClosureCompiler
        disableClassMetadata = true; // activates -XdisableClassMetadata
        disableCastChecking = true; // activates -XdisableCastChecking
    }
}
{% endhighlight %}

## Running the draft war

As described before, the plugn adds a task “draftWar” to create a war file with a draft-compiled version of your GWT modules.
This war can simply be run in a servlet container of your choice.
But it’s also possible to do that using Gradle. The following chapters will show examples, how to configure different gradle plugns to do that for you.

### Jetty plugin

Assumed that you added this plugin with “apply plugin: 'jetty'”, you can define a task “jettyDraftWar” with the following configuration:

{% highlight groovy linenos=table %}
task jettyDraftWar(type: JettyRunWar) {
    dependsOn draftWar
    dependsOn.remove('war')
    webApp=draftWar.archivePath
}
{% endhighlight %}

Simply run that task and navigate your browser to [http://localhost:8080/].

### Cargo plugin

The cargo plugin is not a standard plugin shipped with gradle itself. You can read more about it on (GitHub)[https://github.com/bmuschko/gradle-cargo-plugin].
Assumed that you configured the servlet container of your choice using the documentation of the cargo plugin, you can add the draft war to it by adding the following configuration:

{% highlight groovy linenos=table %}
cargo {
    deployable {
        file = tasks.draftWar.archivePath
        context = 'draft'
    }
}
{% endhighlight %}

If you want the cargo plugin to automatically build the draft war when executed, add the following to the end of your “gradle.build”:

{% highlight groovy linenos=table %}
afterEvaluate {
    tasks.cargoStartLocal.dependsOn(tasks.draftWar)
    tasks.cargoRunLocal.dependsOn(tasks.draftWar)
}
{% endhighlight %}
