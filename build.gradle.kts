plugins {
    kotlin("jvm") version "1.8.20"
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
    testImplementation("io.kotest:kotest-property:5.5.5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.hvpaiva"
            artifactId = "compare-with"
            version = "1.0.0"

            pom {
                name.set("Compare With")
                description.set("Simplified Comparisons in Kotlin")
                url.set("https://github.com/hvpaiva/compare-with")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("hvpaiva")
                        name.set("Highlander Paiva")
                        email.set("hvpaiva@icloud.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/hvpaiva/compare-with.git")
                    developerConnection.set("scm:git:ssh://github.com/hvpaiva/compare-with.git")
                    url.set("https://github.com/hvpaiva/compare-with/")
                }
            }

            from(components["java"])
        }
    }
}