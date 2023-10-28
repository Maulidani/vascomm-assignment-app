plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("com.google.dagger.hilt.android")
//    kotlin("kapt")
}

//// Allow references to generated code
//kapt {
//    correctErrorTypes = true
//}

android {
    namespace = "com.example.assigmenttestvascomm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assigmenttestvascomm"
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
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // retrofit, json converter & okhttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0" )
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    // bill of materials (BOM)
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // image load
    implementation("io.coil-kt:coil:2.2.2")

    // viewmodel & livedata
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

//    // dagger hilt, set in build.gradle (Project:)
//    implementation("com.google.dagger:hilt-android:2.44")
//    kapt("com.google.dagger:hilt-android-compiler:2.44")

}