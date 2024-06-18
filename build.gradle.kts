plugins {
    java
    id("eu.kakde.gradle.sonatype-maven-central-publisher") version "1.0.6"
}

group = "ru.itskekoff.aaio"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("commons-codec:commons-codec:1.15")

    implementation("org.projectlombok:lombok:1.18.28")

    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    withJavadocJar()
    withSourcesJar()
}


object Meta {
    const val COMPONENT_TYPE = "java"
    const val GROUP = "ru.itskekoff.aaio"
    const val ARTIFACT_ID = "AaioPaymentsSDK"
    const val VERSION = "1.0.0"
    const val PUBLISHING_TYPE = "AUTOMATIC"
    val SHA_ALGORITHMS = listOf("SHA-256", "SHA-512")
    const val DESC = "GitHub Version Catalog Repository for Personal Projects based on Gradle"
    const val LICENSE = "Apache-2.0"
    const val LICENSE_URL = "https://opensource.org/licenses/Apache-2.0"
    const val GITHUB_REPO = "itskekoff/AaioPaymentsSDK.git"
    const val DEVELOPER_ID = "itskekoff"
}

val sonatypeUsername: String? by project
val sonatypePassword: String? by project


sonatypeCentralPublishExtension {
    groupId.set(Meta.GROUP)
    artifactId.set(Meta.ARTIFACT_ID)
    version.set(Meta.VERSION)
    componentType.set(Meta.COMPONENT_TYPE)
    publishingType.set(Meta.PUBLISHING_TYPE)

    username.set(System.getenv("SONATYPE_USERNAME") ?: sonatypeUsername)
    password.set(System.getenv("SONATYPE_PASSWORD") ?: sonatypePassword)

    // Configure POM metadata
    pom {
        name.set(Meta.ARTIFACT_ID)
        description.set(Meta.DESC)
        url.set("https://github.com/${Meta.GITHUB_REPO}")
        licenses {
            license {
                name.set(Meta.LICENSE)
                url.set(Meta.LICENSE_URL)
            }
        }
        developers {
            developer {
                id.set(Meta.DEVELOPER_ID)
            }
        }
        scm {
            url.set("https://github.com/${Meta.GITHUB_REPO}")
            connection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
            developerConnection.set("scm:git:https://github.com/${Meta.GITHUB_REPO}")
        }
        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/${Meta.GITHUB_REPO}/issues")
        }
    }
}