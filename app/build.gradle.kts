plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.app.olders_rrss"
    compileSdk = 34

    /* Para Google Cloud
    packaging {
        resources {
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/INDEX.LIST")
        }
    }     */

    defaultConfig {
        applicationId = "com.app.olders_rrss"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Room cannot export the schema:
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["kapt.kotlin.generated"] = "$projectDir/generated"
            }
        }
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("kapt.kotlin.generated", "$projectDir/generated")
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    viewBinding {
        enable = true
    }
    buildToolsVersion = "34.0.0"
}



dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.9.23")

    // GSON
    implementation("com.google.code.gson:gson:2.10.1")

    // Para Room
    // https://www.youtube.com/watch?v=hvjaTQmc2Xo
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.activity:activity-ktx:1.8.2")

    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")


    // Para corutinas
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

// En formato Kotlin DSL (build.gradle.kts)
    implementation("androidx.room:room-runtime:2.6.1")

    // Para Google Cloud
    //implementation ("com.google.cloud:google-cloud-language:2.37.0")
}
