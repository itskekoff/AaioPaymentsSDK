plugins {
    java
    `maven-publish`
}

group = "ru.itskekoff.aaio"
version = "1.0.0"

repositories {
    mavenCentral()
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

java {
    withSourcesJar()
    withJavadocJar()
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

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
            setArtifacts(configurations.archives.get().allArtifacts)
        }
    }
}