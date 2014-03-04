---
layout : default
title :  "Draft war"
---

## What is a draft war?

A full build of a GWT project can be very time consuming depending on the size of the project.
During development you do not always need a production quality version of your application.
That's why developers often change the *.gwt.xml files or compiler flags locally to speed up compilation. As this is dirty and you have to be very careful to not commit those cages to the VCS, this plugin provides special support for this use-case.

Besides the "compileGwt" task there's a task called "draftCompileGwt". This task has all optimization settings turned of by default but the "draftCompile" flag turned on.
This makes it possible to easily start a fast compilation without changing the build script or any other file.

In addition to this, a task called "draftWar" is added that makes it possible to build a war using the output of "draftCompileGwt" instead of the output of "compileGwt". If you customized the war task you have to do the same with the "draftWar" task if the changes are essential for the application to be runnable.

## Different sets of GWT modules

To make this feature really useful, you can specify a second set of GWT modules that are used for development purposes:

{% highlight groovy linenos=table %}
gwt {
    modules 'de.richsource.gradle.plugins.gwt.example.Example'
    devModules 'de.richsource.gradle.plugins.gwt.example.ExampleDev'
}
{% endhighlight %}

The development module typically includes the normal/production GWT module. By doing this you can specify specific settings for the development version of your GWT module like setting "collapse-all-properties" which will result in only one big permutation during development.
To make the development module work with your html pages that reference your normal GWT module, use the same "rename-to" property for both of your *.gwt.xml files.
You can also specify a reduced set of available languages or other things to speed up compilation ... 

A development *.gwt.xml typically looks like this:

{% highlight xml linenos=table %}
<module rename-to="app">
	<inherits name="de.richsource.gradle.plugins.gwt.example.Example" />
	<collapse-all-properties />
</module>
{% endhighlight %}

## Running the draft war

As described before, the plugin adds a task “draftWar” to create a war file with a draft-compiled version of your GWT modules.
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

If you want the cargo plugin to automatically build the draft war when executed, add the following to the end of your “build.gradle”:

{% highlight groovy linenos=table %}
afterEvaluate {
    tasks.cargoStartLocal.dependsOn(tasks.draftWar)
    tasks.cargoRunLocal.dependsOn(tasks.draftWar)
}
{% endhighlight %}
