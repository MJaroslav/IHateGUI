plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation(Dependencies.SHARED_JAVA)

    annotationProcessor(Dependencies.JABEL)
    testAnnotationProcessor(Dependencies.JABEL)

    compileOnly(Dependencies.LOMBOK)
    annotationProcessor(Dependencies.LOMBOK)
    testCompileOnly(Dependencies.LOMBOK)
    testAnnotationProcessor(Dependencies.LOMBOK)

    compileOnly(Dependencies.JB_ANNOTATIONS)
    testCompileOnly(Dependencies.JB_ANNOTATIONS)

    testImplementation(Dependencies.JUNIT_JUPITER)
    testRuntimeOnly(Dependencies.JUNIT_PLATFORM)
}

java {
    withSourcesJar()
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = "16"
    options.release = 8
    options.encoding = "UTF-8"

    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(16)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
