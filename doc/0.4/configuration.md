---
layout : default
title :  "Configuration"
---

### Plugin configuration

The plugin registers an extension named "gwt" of type [GwtPluginExtension](javadoc/de/richsource/gradle/plugins/gwt/GwtPluginExtension.html) with the Gradle model. This extension defines the conventions/defaults for all GWT related tasks. If you use the extension to do the configuration, the plugin ensures that the configuration properties are consistently set as default values to all related tasks.
All properties that are common to multiple/all GWT related tasks are defined in the extension itself (e.g. used GWT modules). Properties that a re specific to one kind of task are defined in specific sub-objects.

An example of both kinds of properties looks this way:

{% highlight groovy linenos=table %}
gwt {
    gwtVersion='2.6.0'

    modules 'de.richsource.gradle.plugins.gwt.example.Example'
    
    compiler {
        strict = true;
        enableClosureCompiler = true;
        disableClassMetadata = true;
        disableCastChecking = true;
    }
    
    dev {
        noServer = true
        port = 1337
    }
}
{% endhighlight %}

In addition to specifying properties in the plugin extension, it's also possible to overwrite the defaults for specific tasks.

An example of configuring the "compileGwt" task to use different GWT modules looks like this:

{% highlight groovy linenos=table %}
compileGwt {
    modules = ['de.richsource.different.module']
}
{% endhighlight %}

### Available configuration parameters

In the following list you can find the interfaces/classes that define specific tasks as well as their associated configuration object in the plugin extension:

* compileGwt ([GwtCompile](javadoc/de/richsource/gradle/plugins/gwt/GwtCompile.html))
  * Extension object: compiler ([GwtCompileOptions](javadoc/de/richsource/gradle/plugins/gwt/GwtCompileOptions.html))
* gwtDev ([GwtDev](javadoc/de/richsource/gradle/plugins/gwt/GwtDev.html))
  * Extension object: dev ([GwtDevOptions](javadoc/de/richsource/gradle/plugins/gwt/GwtDevOptions.html))
* gwtSuperDev ([GwtSuperDev](javadoc/de/richsource/gradle/plugins/gwt/GwtSuperDev.html))
  * Extension object: dev ([GwtSuperDevOptions](javadoc/de/richsource/gradle/plugins/gwt/GwtSuperDevOptions.html))

### Test configuration

The plugin's support of testing is not based on a custom task but instead extends the existing Test task of Gradle.

To do this, every instance of Gradle's Test task is dynamically extended to have a prperty "gwt" of type [GwtTestExtension](javadoc/de/richsource/gradle/plugins/gwt/GwtTestExtension.html).
In addition, an instance of [GwtTestOptions](javadoc/de/richsource/gradle/plugins/gwt/GwtTestOptions.html) is added as property "test" to the plugin's extension object. Using this, you can again specify defaults for all instances of the Test task.

To activate the manipulation of Test tasks to support GWT tests, you have to add the foollowing to your build.gradle:

{% highlight groovy linenos=table %}
gwt {
    test {
        hasGwtTests = true
    }
}
{% endhighlight %}

## Common cases

### Memory settings

To change the memory settings for all GWT related tasks, you can use the following:

{% highlight groovy linenos=table %}
gwt {
    minHeapSize = "512M";
	maxHeapSize = "1024M";
}
{% endhighlight %}

To change those settings for only one task you can also specify these settings on a task level:

{% highlight groovy linenos=table %}
compileGwt {
    minHeapSize = "512M";
	maxHeapSize = "1024M";
}
{% endhighlight %}

### Log level

The log level of GWT tasks is automatically configured depending on Gradle's log level. So by default this is "ERROR".

If you change Gradle's log level (e.g. by adding "--info" or "--debug" on the command line), this also affects the log level of GWT related tasks.

To change the log level independent of Gradle's log level, you can do this:

{% highlight groovy linenos=table %}
gwt {
    logLevel = 'INFO'
}
{% endhighlight %}

The availeble log levels are defined in the enum [LogLevel](javadoc/de/richsource/gradle/plugins/gwt/LogLevel.html).
