plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ruizrabadanjuanpedro.pmdm_tarea2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ruizrabadanjuanpedro.pmdm_tarea2"
        minSdk = 31
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

    buildFeatures  {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-splashscreen:1.0.1")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.1")

    implementation (libs.appcompat)
    implementation (libs.material)
    implementation (libs.activity)
    implementation (libs.constraintlayout)
    testImplementation (libs.junit)
    androidTestImplementation (libs.ext.junit)
    androidTestImplementation (libs.espresso.core)
}