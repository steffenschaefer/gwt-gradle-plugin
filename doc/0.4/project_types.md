---
layout : default
title :  "Project types"
---

## Web application

Using gwt-gradle-plugin to configure a web application project is the most common use-case.
[Quickstart](quickstart) shows how to configure GWT based web application projects.

## Library project

If you create your own GWT library (e.g. widget library) you will only need some of the funtionality provided by the plugin.
What you need for this kind of project are basically the GWT dependencies.
As such a project does not provide an entry point, there's nothing to compile or run.

To configure a library project, simply use the "gwt-base" plugin instead of applying "gwt" and "war" plugins. Additionally, you do not have to specify any GWT module.

    buildscript {
        [...]
    }

    apply plugin: 'gwt-base'

    gwt {
        gwtVersion='2.6.0'
    }

## Compile only

This is the kind of project if you use GWT without deploying your application as a *.war file. In this case you typically don't use the "war" plugin but running the GWT compiler is needed.
To configure this kind of project, you can use the "gwt-compiler" plugin:

    buildscript {
        [...]
    }

    apply plugin: 'war'
    apply plugin: 'gwt'

    gwt {
        gwtVersion='2.6.0'
        modules '<YOUR-GWT-MODULE>'
    }

As nobody but you knows what you want to do with the GWT compiler output, the plugin does nothing else than configuring the task "compileGwt". You are responsible the this task is called by setting appropriate task dependencies.

Typical use-cases for this are:
* Uploading the GWT compiler output to a maven repository to be consumed by other projects.
* Consuming the GWT compiler output an another project of a multi-module build

The first use-case can be configured the following way:

    buildscript {
        [...]
    }

    apply plugin: 'gwt-compiler'
    apply plugin: 'maven-publish'

    gwt {
        gwtVersion='2.6.0'
        modules '<YOUR-GWT-MODULE>'
    }
    
    task gwtZip(type: Zip) {
        from tasks.compileGwt.outputs
    }
    
    group='<YOUR-GROUP-ID>'
    version='<YOUR-VERSION>'
    publishing {
        publications {
            mavenJava(MavenPublication) {
                artifact gwtZip {
                    extension = 'zip'
                    classifier = 'gwt'
                }
            }
        }
        repositories {
            maven {
                url project.file('repo').toURI()
            }
        }
    }

The second use-case can be set up in the following way if the consuming project is a web project:

    apply plugin: 'war'
    
    war {
        from project(':compile-only').tasks.compileGwt.outputs
    }
