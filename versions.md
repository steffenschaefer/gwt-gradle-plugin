---
layout : default
title :  "Documentation overview"
---

## Version history

### 0.3 (2014-01-09)

* Compatibility
    * Gradle 1.8+ (tested 1.8-1.10)
    * GWT 2.x (tested 2.5.1)
* Resources
    * [Documentation](doc/0.3/)
    * [JavaDocs](doc/0.3/javadoc/)
* Changes/fixes
    * The plugin was split into several smaller plugins (gwt, gwt-base, gwt-compiler) to better work with GWT libraries and non-webapp projects.

### 0.2 (2013-12-08)

* Compatibility
    * Gradle 1.6+ (tested 1.6-1.9)
    * GWT 2.x (tested 2.5.1)
* Resources
    * [Documentation](doc/0.2/)
    * [JavaDocs](doc/0.2/javadoc/)
* Changes/fixes
    * Improved JavaDocs (including external links)
    * The log level of GWT related tasks is now bound to Gradle's log level by default
    * Addition of "gwtSdk" configuration to fix Eclipse classpath when using GWT_CONTAINER
* Breaking changes
    * If you manually define your GWT dependencies, you have to change gwt-dev and gwt-user to be added to the new gwtSdk configuration.

### 0.1 (2013-10-18)

* Compatibility
    * Gradle 1.6+ (tested 1.6-1.8)
    * GWT 2.x (tested 2.5.1)
* Resources
    * [Documentation](doc/0.1/)
    * [JavaDocs](doc/0.1/javadoc/)
* Changes
    * Initial release
