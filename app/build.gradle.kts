@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

val omdbApiKey = project.findProperty("OMDB_API_KEY") as String? ?: ""

android {
    namespace = "pl.dmardev172.movieexplorer"
    compileSdk = 34

    defaultConfig {
        applicationId = "pl.dmardev172.movieexplorer"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "OMDB_API_KEY",
            "\"$omdbApiKey\""
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // 1.4.3
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation("androidx.navigation:navigation-compose:2.7.7")
    testImplementation("junit:junit:4.12")

    // Koin for Android
    val koinVersion = "3.5.6"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    // Retrofit
    val retrofitVersion = "2.11.0" // 2.9.0
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    // Moshi
    val moshiVersion = "1.15.1"
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    // implementation("com.squareup.okhttp3:okhttp:4.12.0") //4.11.0

    // Coil for Compose
    implementation("io.coil-kt:coil-compose:2.6.0")

    // implementation("androidx.multidex:multidex:2.0.1")

    // Tests
    testImplementation(libs.junit)
    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    // Turbine as optional - for Flow testing
//     testImplementation("app.cash.turbine:turbine:1.0.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    testImplementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    testImplementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}