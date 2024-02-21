plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.app.olders_rrss"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.olders_rrss"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

        // SE prueba y se agrego por Room
        //freeCompilerArgs = listOf("-Xuse-experimental=kotlin.Experimental")
    }

    buildFeatures {
        viewBinding = true
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
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.9.22")

    // GSON
    implementation("com.google.code.gson:gson:2.10.1")


    // Para Room
    // https://www.youtube.com/watch?v=hvjaTQmc2Xo
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    kapt("androidx.room:room-compiler:2.6.1")

    // Para corutinas
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

// En formato Kotlin DSL (build.gradle.kts)
    implementation("androidx.room:room-runtime:2.6.1")
    //kapt("androidx.room:room-compiler:2.4.0")



    //testImplementation("junit:junit:4.13.2")
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //implementation("edu.stanford.nlp:kotlinnlp:0.1.0")
    //implementation("edu.stanford.nlp:stanford-corenlp:4.5.5")

    /*
        // implementar Natural Language Toolkit (NLTK)
            implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.2")

            implementation("com.beust:klaxon:5.6")
            implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

            implementation("org.nltk:nltk:3.6.3")
    */
    //implementation("org.languagetool:languagetool-core:4.8")


    //implementation("org.apache.opennlp:opennlp-tools:1.9.3") No esta el modelo en español pero compila correctamente

    //  spaCy
    //implementation("io.github.explosion:spacy:3.2.1")
    //implementation("org.jetbrains.kotlinx:kotlinx-python:0.14.0")

    // TENSOR FLOW
    //implementation("org.tensorflow.keras:tensorflow-keras-core:2.6.0")
    //implementation("org.tensorflowlite:tensorflowlite-kotlin:2.6.0")
    //implementation("org.tensorflow:tensorflow-core-ktx:2.14.0")
    //implementation ("org.tensorflow:tensorflow-core:2.10.0")

}
