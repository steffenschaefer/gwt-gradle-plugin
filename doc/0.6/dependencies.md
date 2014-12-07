---
layout : default
title :  "GWT Dependencies"
---

## GWT specific configurations

The plugin defines two custom Gradle configurations:

* gwtSdk: This configuration is used for GWT SDK itself excluding gwt-servlet (gwt-dev, gwt-user)
* gwt: This configuration should be used to specify all GWT client specific dependencies (e.g. widget libraries as GXT)

As "gwt-servlet" is used on the server-side, you typically add it to Gradles's "runtime" configuration.

## Automatic dependency management

The plugin can automatically add the needed GWT dependencies (gwt-dev, gwt-servlet, ...) for you. Everything you have to do is:
* Provide a repository where the GWT artifacts are hosted.
    If you haven’t already configured a repository and you want to use the Maven central repository, add the following to your build.gradle file:
    repositories { mavenCentral() }
* Define the GWT version in the format "major.minor.patch" or "major.minor.patch-xxx" (e.g. "2.6.0" or "2.6.0-rc3". To do that, insert the following configuration to your “build.gradle” file:
    gwt { gwtVersion='2.6.0' }

If you do not specify the "gwtVersion" property, it is assumed that you are adding the dependencies to the correct configurations by yourselves.

## Adding additional libraries

As mentioned before, additional GWT client specific libraries have to be added to the "gwt" configuration.
If the binary jar does not contain the source code of the library, you also have to declare the source as dependency.

{% highlight groovy linenos=table %}
dependencies {
	gwt 'foo:bar:1.2.3'  
	gwt group: 'foo', name: 'bar', version: '1.2.3', classifier: 'sources'
}
{% endhighlight %}
