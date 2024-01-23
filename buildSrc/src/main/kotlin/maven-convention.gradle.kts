plugins {
    `maven-publish`
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}
