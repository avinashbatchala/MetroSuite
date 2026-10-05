plugins {
    id("com.android.library") version "9.3.1"
}

group = "com.metro"

android {
    namespace = "com.metro.livetile.contract"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
