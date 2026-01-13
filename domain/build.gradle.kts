plugins {
    alias(libs.plugins.kotlin.jvm)
    `java-library`
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    api(libs.bundles.core.common)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.jupiter.engine)
}