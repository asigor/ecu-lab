plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.eculab.mobile"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.eculab.mobile"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

val buildWebApp by tasks.registering(Exec::class) {
    workingDir(rootProject.projectDir)
    commandLine("npm", "run", "build")
    inputs.dir(rootProject.file("src"))
    inputs.file(rootProject.file("index.html"))
    inputs.file(rootProject.file("package.json"))
    outputs.dir(rootProject.file("dist"))
}

val copyWebAssets by tasks.registering(Copy::class) {
    dependsOn(buildWebApp)
    from(rootProject.file("dist"))
    into(layout.projectDirectory.dir("src/main/assets/www"))
}

tasks.named("preBuild").configure {
    dependsOn(copyWebAssets)
}
