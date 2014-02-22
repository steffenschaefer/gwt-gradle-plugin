---
layout : default
title :  "Quickstart"
---

## Basic configuration of a GWT web application

This chapter will guide you through the first steps to configure gwt-gradle-plugin for your web application project.

### Plugin dependency

As it is not a core Gradle plugin, you have to ensure, that Gradle knows how to get the plugin. To do this, add the following lines to your build.gradle:

{% highlight groovy linenos=table %}
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

### GWT dependencies
    
If you want to use automatically configured GWT dependencies (gwt-dev, gwt-user, ...) you have to extend the configuration to set the desired GWT version:

{% highlight groovy linenos=table %}
gwt {
    gwtVersion='2.6.0'
    modules '<YOUR-GWT-MODULE>'
}
{% endhighlight %}

### Activating compiler optimizations

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


## Gradle tasks

To build the *.war file including your compiled GWT modules, simply call "gradle build".
If you want to start the GWT development mode simply call "gradle gwtDev".

## How to debug Development Mode?

When running the task “gwtDev” you can specify a system property “-DgwtDev.debug=true” to enable debugging. This causes the build to stop when starting development mode and waiting for a debuger to attach to port 5005.
Now you can configure your IDE to connect to that debug port.