// TODO: Add shadow task or plugin
import net.minecraftforge.gradle.user.patch.UserPatchExtension

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Forge"
            url = uri("https://maven.minecraftforge.net/")
        }
        maven {
            name = "JitPack"
            url = uri("https://jitpack.io")
        }
    }
    dependencies {
        classpath(Dependencies.FORGE_GRADLE) {
            isChanging = false
        }
    }
}

repositories {
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
}

apply(plugin = "forge")
apply(from = "https://raw.githubusercontent.com/MJaroslav/MCInGameTester/master/gradle/configurations/v1.gradle")

configure<UserPatchExtension> {
    version = "1.7.10-10.13.4.1614-1.7.10"
    runDir = "run"
    replace("@VERSION@", project.version)
}

tasks.register<Jar>("devJar") {
    archiveClassifier = "dev"
    manifest { from(project.tasks.jar.get().manifest) }
    from(sourceSets.main.get().output)
}

artifacts {
    archives(tasks.named("devJar"))
}

tasks.processResources {
    inputs.property("version", version)
    inputs.property("mcversion", project.the<UserPatchExtension>().version)
    from(sourceSets.main.get().resources.srcDirs) {
        include("mcmod.info")
        expand("version" to version, "mcversion" to project.the<UserPatchExtension>().version)
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

dependencies {
    implementation(project(":core"))
}
