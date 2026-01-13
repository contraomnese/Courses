plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.contraomnese.courses.features.interests"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))
    implementation(project(":core:presentation"))
    implementation(project(":domain"))

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.jupiter.engine)
    androidTestImplementation(libs.bundles.androidTest)
    debugImplementation(libs.bundles.composeDebug)
}