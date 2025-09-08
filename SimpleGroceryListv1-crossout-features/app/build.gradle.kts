plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {


    namespace = "com.example.simplegrocerylist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.simplegrocerylist"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }




}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.xsupport.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.10")

    //CardView
    implementation(libs.androidx.cardview)
    //RecyclerView
    implementation(libs.androidx.recyclerview)
    //Room
    implementation(libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)
    //LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    kapt(libs.lifecycle.compiler)
    implementation(libs.androidx.activity.ktx)
    //ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)


    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.activity)
}
