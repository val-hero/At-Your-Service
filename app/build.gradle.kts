plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.atyourservice"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.atyourservice"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    //Core
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.fragment:fragment-ktx:1.6.0")

    //MVVM
    val lifecycleVer = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycleVer}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycleVer}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${lifecycleVer}")

    //Koin
    val koinVer = "3.4.1"
    implementation("io.insert-koin:koin-android:${koinVer}")
    implementation("io.insert-koin:koin-core:${koinVer}")

    //Google Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.0")

    //Retrofit + Gson
//    val retrofitVer = "2.9.0"
//    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("com.squareup.retrofit2:converter-gson:${retrofitVer}")
//    implementation("com.squareup.retrofit2:retrofit:${retrofitVer}")

    //Glide
//    implementation("com.github.bumptech.glide:glide:4.15.1")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}