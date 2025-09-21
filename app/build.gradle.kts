import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import org.gradle.kotlin.dsl.implementation

// app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // You might want to remove this if fully migrated to KSP
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
   // id("com.apollographql.apollo3")  // Add Apollo plugin

}

android {
    namespace = "com.example.denoapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.denoapplication"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Kotlin Coroutines (only latest version)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")  // OkHttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")  // OkHttp Logging
    implementation("com.google.code.gson:gson:2.8.8") // Gson

    // Jackson (optional)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")

    // ... other dependencies
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0") // Changed from kapt to ksp

    // Hilt (Dependency Injection) - Already correctly using ksp
    implementation ("com.google.dagger:hilt-android:2.57.1" )// Latest Hilt version
    kapt("com.google.dagger:hilt-android-compiler:2.57.1")


    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    implementation ("androidx.room:room-runtime:2.8.0")
    kapt ("androidx.room:room-compiler:2.8.0")

    // Kotlin coroutines support for Room
    implementation ("androidx.room:room-ktx:2.8.0")

   // implementation("com.apollographql.apollo3:apollo-runtime:3.8.3")  // Add Apollo runtime


}
/*

apollo {
    // Optional: Specify the package for generated classes
    packageName.set("com.example.denoapplication.graphql")
}*/
