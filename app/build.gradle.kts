import dagger.hilt.android.plugin.util.capitalize
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.currencyconverter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.currencyconverter"
//        applicationId = "com.currencyconverter"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
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
        viewBinding = true
    }
    androidComponents {
        onVariants(selector().all()) { variant ->
            afterEvaluate {
                project.tasks.getByName("ksp" + variant.name.capitalize() + "Kotlin") {
                    val dataBindingTask =
                        project.tasks.getByName("dataBindingGenBaseClasses" + variant.name.capitalize()) as (com.android.build.gradle.internal.tasks.databinding.DataBindingGenBaseClassesTask)

                    (this as (org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>)).setSource(
                        dataBindingTask.sourceOutFolder
                    )
                }
            }
        }
    }

}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")


    //others
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("org.jsoup:jsoup:1.16.1")
    implementation("com.airbnb.android:lottie:6.1.0")

}