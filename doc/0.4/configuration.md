---
layout : default
title :  "Configuration"
---

## Plugin extension structure

The plugin registers an extension named "gwt" of type [GwtPluginExtension](javadoc/de/richsource/gradle/plugins/gwt/GwtPluginExtension.html) with the Gradle model. This extension defines the conventions/defaults for all GWT related tasks. If you use the extension to do the configuration, the plugin ensures that the configuration properties are consistently set to all related tasks.

The basic structure of this extension is shown in the following example:

{% highlight groovy linenos=table %}
gwt {
    TODO
}
{% endhighlight %}

TODO


## Task configuration

Sometimes it's necessary to configure a task different to the properties defined in the plugin extension.

TODO

## Common cases

### Memory settings

TODO

### Additional source directories

TODO
