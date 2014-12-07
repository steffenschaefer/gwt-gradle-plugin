---
layout : default
title :  "Version history"
---

## Versions

### 0.6 (2014-12-07)

* Compatibility
    * Gradle 1.6+ (tested 2.0 - 2.1)
    * GWT 2.x (tested 2.7.0)
* Resources
    * [Documentation](doc/0.6/)
    * [JavaDocs](doc/0.6/javadoc/)
* Changes/fixes
    * Added new flags introduced in GWT 2.7 and 2.6 ([GitHub issue #55](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/55))
    * Added new task 'checkGwt' for validation of GWT sources ([GitHub issue #58](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/58))
    * Fixed configuration of test task for GWT ([GitHub issue #57](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/57))

### 0.5 (2014-09-08)

* Compatibility
    * Gradle 1.6+ (tested 1.12 - 2.1)
    * GWT 2.x (tested 2.6.1)
* Resources
    * [Documentation](doc/0.5/)
    * [JavaDocs](doc/0.5/javadoc/)
* Changes/fixes
    * Fixed devModules property in GwtPluginExtension ([GitHub issue #40](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/40))
    * Added new Flag 'useClasspathForSrc' to GwtSuperDevOptions/GwtSuperDev to work around a bug in GWT (GitHub issues [#34](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/34) and [#37](https://github.com/steffenschaefer/gwt-gradle-plugin/issues/37))
    * Added example with non-Maven layout
    * Several cosmetic changes

### 0.4 (2014-03-21)

* Compatibility
    * Gradle 1.6+ (tested 1.6-1.11)
    * GWT 2.x (tested 2.6.0)
* Resources
    * [Documentation](doc/0.4/)
    * [JavaDocs](doc/0.4/javadoc/)
* Changes/fixes
    * Internal changes to improve compatibility with several Gradle versions
    * Fixed an issue that caused incompatibilities with Gradle 1.12
    * Added more example projects
    * Improved and extended the documentation
* Breaking changes
    * The inheritance of several tasks is changed. Do a clean build if anything behaves strange.

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
