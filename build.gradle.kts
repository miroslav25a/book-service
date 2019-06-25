plugins {
    java
}

repositories {
    jcenter()
}

dependencies {
    compile("com.google.guava:guava:18.0")
    compile( "org.apache.commons:commons-lang3:3.3.2")
    compile("org.slf4j:slf4j-api:1.7.26'")
    compile("org.slf4j:slf4j-simple:1.7.26")

    testCompile("junit:junit:4.+")
    testCompile("org.assertj:assertj-core:3.12.2")
    testCompile("org.hamcrest:hamcrest-core:2.1")
}

tasks.test {
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STANDARD_ERROR", "STANDARD_OUT")
    }
}