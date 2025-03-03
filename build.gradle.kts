plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "org.fastcampus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // lombok
    implementation ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")

    // querydsl
    implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor ("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor ("jakarta.persistence:jakarta.persistence-api")

    // test
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * QueryDSL Build Options
 */
val querydslDir = layout.buildDirectory.dir("generated/sources/annotationProcessor/java/main")

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
    options.generatedSourceOutputDirectory.set(querydslDir)
}

// ✅ QueryDSL 빌드 시 생성된 파일 삭제
tasks.named("clean") {
    doLast {
        querydslDir.get().asFile.deleteRecursively()
    }
}