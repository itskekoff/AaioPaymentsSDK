plugins {
    java
    `maven-publish`
}

group = "ru.itskekoff.aaio"
version = "1.1"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
            pom {
                name.set(project.name)
                description.set("A application to interact with aaio payment api")
                url.set("https://github.com/itskekoff/AaioPaymentsSDK")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("itskekoff")
                        name.set("Pablo")
                        email.set("itskekoff@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/itskekoff/AaioPaymentsSDK.git")
                    developerConnection.set("scm:git:ssh://github.com:itskekoff/AaioPaymentsSDK.git")
                    url.set("https://github.com/itskekoff/AaioPaymentsSDK")
                }
            }
        }
    }
}