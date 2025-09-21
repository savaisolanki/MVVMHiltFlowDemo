plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add Apollo Gradle plugin
       // classpath("com.apollographql.apollo3:apollo-gradle-plugin:3.8.3") // Use latest version from https://github.com/apollographql/apollo-kotlin
    }
}
