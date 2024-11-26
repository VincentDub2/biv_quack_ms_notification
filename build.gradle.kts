plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	id("jacoco")
}

group = "fr.polytech"
version = "0.0.1-SNAPSHOT"
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

jacoco {
	toolVersion = "0.8.12"
}

tasks.test {
	useJUnitPlatform()
	finalizedBy("jacocoTestReport") // Génère un rapport après les tests
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // S'assurer que les tests sont exécutés avant le rapport
	reports {
		xml.required.set(true) // Générer un rapport XML (utile pour CI/CD ou SonarQube)
		html.required.set(true) // Générer un rapport HTML pour une analyse locale
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml")) // Chemin du rapport HTML
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.mapstruct:mapstruct:1.5.2.Final")
	implementation("javax.persistence:javax.persistence-api:2.2")

	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	testImplementation("com.h2database:h2")

	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.2.Final")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}
