import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.contraomnese.courses"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.contraomnese.courses"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = gradleLocalProperties(rootDir, providers).getProperty("KEY_ALIAS")
            keyPassword = gradleLocalProperties(rootDir, providers).getProperty("KEY_PASSWORD")
            storeFile = file(gradleLocalProperties(rootDir, providers).getProperty("STORE_FILE"))
            storePassword = gradleLocalProperties(rootDir, providers).getProperty("STORE_PASSWORD")
        }
    }

    buildTypes {
        debug {

        }

        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("boolean", "DEBUG", "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

configurations.all {
    resolutionStrategy {
        force("androidx.activity:activity-compose:1.9.3")
        force("androidx.activity:activity-ktx:1.9.3")
        force("androidx.activity:activity:1.9.3")
    }
}

dependencies {

    // modules
    implementation(project(":core:ui"))
    implementation(project(":core:design"))
    implementation(project(":core:navigation"))
    implementation(project(":core:presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(project(":features:auth"))
    implementation(project(":features:home"))
    implementation(project(":features:bottom_menu"))

//    "baselineProfile"(project(":baselineprofile"))
//    implementation(libs.bundles.profiler)

    implementation(libs.material)
    implementation(libs.bundles.network)
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.datastore)

    implementation("dev.chrisbanes.haze:haze-materials:1.7.1")


    implementation(libs.androidx.core.splashscreen)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.jupiter.engine)
    androidTestImplementation(libs.bundles.androidTest)
    debugImplementation(libs.bundles.composeDebug)
}