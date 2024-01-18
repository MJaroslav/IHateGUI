plugins {
    `maven-publish`
}

publishing {
    publications {
        val maven by this.creating(MavenPublication::class) {
            from(components["java"])
        }
    }
}
