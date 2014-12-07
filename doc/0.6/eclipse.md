---
layout : default
title :  "Working with Eclipse"
---

## Project layout

TODO GPE vs Maven/Gradle Standard-Layout

## Project import

In general there are two methods of importing Gradle projects into Eclipse:

1. Running "gradle eclipse" and import the projects as existing projects
2. By using [Gradle integration for Eclipse](https://marketplace.eclipse.org/content/gradle-integration-eclipse) ([GitHub repository](https://github.com/spring-projects/eclipse-integration-gradle/))

Both methods have advantages and the gwt-gradle-plugin doesn't force you to use either one.

## Working with "gradle eclipse"

Importing projects using this method should be working out of the box as the plugin manipulates the eclipse model accordingly. What you have to do is:

1. Apply the Gradle plugin "eclipse" by adding "apply plugin: 'eclipse'" to your build.gradle
2. Run "gradle eclipse" in your project
3. In Eclipse go to File > Import and import the project as "Existing Projects into Workspace"

Whenever you change something that is relevant for eclipse, simply rerun "gradle eclipse".

## Working with Gradle Integration for Eclipse

Importing a project to Eclipse using this approach can be a little bit tricky, but if you pay attention to some details, this will work very well.

As the gwt-gradle-plugin must specify special settings for the Eclipse configuration, you have to "apply plugin: 'eclipse'" in your build.gradle.

Assumed you already installed Gradle Integration for Eclipse, the following steps are necessary to import the project to your Eclipse workspace:

1. Open the File > Import... dialog and select "Gradle Project" in the list
2. Select the root of your Gradle project
3. Press "Build Model"
4. Select your project in the tree
5. Adjust the parameters as needed. Pay special attention to set "Run before" to the default value "cleanEclipse eclipse". Other settings will work too, but leaving this setting empty will not(!)

If you have enable "dependency management" and you are faced with Classloading issues that do not occur in Gradle, try the following: Go to the project settings and open the page "gradle". Select "As returned by build script" for "Classpath sorting strategy".

Whenever you change something that is relevant for eclipse, right-click on your project and select best-matching "Refresh" command in the menu named "Gradle".
