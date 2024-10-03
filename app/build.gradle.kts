plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.roomdatabaselibraryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.roomdatabaselibraryapp"
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
}

dependencies {
    // Existing dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room database dependencies
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    // Annotation processor for Room
    annotationProcessor(libs.room.compiler)
}
