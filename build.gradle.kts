plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
    id("io.github.intisy.github-gradle") version "1.8.2.1"
}

group = "io.github.elenawawa"
description = "Serenity"

apply(from = "https://raw.githubusercontent.com/Slimefun5/gradle/refs/heads/main/slimefun-addon.gradle")

dependencies {
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
    implementation("org.bstats:bstats-bukkit:2.2.1")
}

tasks {
    shadowJar {
        relocate("org.bstats", "extragear.libs.bstats")
        relocate("io.github.mooy1.infinitylib", "io.github.mooy1.infinityexpansion.infinitylib")
        minimize()
    }
}
