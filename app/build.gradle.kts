plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.nit3213finalapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nit3213finalapp"
        minSdk = 21
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Android Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // RecyclerView for Dashboard List
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Retrofit for API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines for Asynchronous Work
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Lifecycle ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("com.google.android.material:material:1.6.0")

    // Navigation Component for Navigation Graph
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Hilt for Dependency Injection
    implementation("com.google.dagger:hilt-android:2.48")
    implementation(libs.junit.junit)
    kapt("com.google.dagger:hilt-compiler:2.48")


    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation("io.mockk:mockk:1.13.12")
}
