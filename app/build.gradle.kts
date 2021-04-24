import java.io.FileInputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val urlProperties = rootProject.file("Project.properties")
val properties = Properties()
properties.load(FileInputStream(urlProperties))

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "io.bibuti.opennews"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")
        setProperty("archivesBaseName", getArtifactName(artifact = this))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                    hashMapOf(
                        "room.schemaLocation" to "$projectDir/schemas"
                    )
                )
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("default")
    productFlavors {
        create("dev") { //also know as staging/internal testing, etc
            buildConfigField("String", "BASE_URL", properties["STAGING_BASE_URL"].toString())
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                properties["STAGING_NEWS_API_KEY"].toString()
            )
        }
        create("production") {
            buildConfigField("String", "BASE_URL", properties["PRODUCTION_BASE_URL"].toString())
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                properties["PRODUCTION_NEWS_API_KEY"].toString()
            )
        }
    }

    buildFeatures.dataBinding = true

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")


    //de-sugaring for using latest java 8 features on older devices
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    //logging with timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    //navigation..
    val fragmentKtx = "2.3.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$fragmentKtx")
    implementation("androidx.navigation:navigation-ui-ktx:$fragmentKtx")

    //lifecycle..
    val lifecycleKtx = "2.3.0"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleKtx")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleKtx")

    //coroutines..
    val coroutines = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    //retrofit..
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //database..
    val room = "2.2.6"
    implementation("androidx.room:room-runtime:$room")
    kapt("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

    //paging..
    implementation("androidx.paging:paging-runtime-ktx:2.1.2")

    //glide..
    implementation("com.github.bumptech.glide:glide:4.12.0")

    //dependency injection..
    val hiltVersion = "2.35"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    //chucker intercepter
    val chucker = "3.4.0"
    debugImplementation("com.github.chuckerteam.chucker:library:$chucker")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:$chucker")


    //testing..
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

}

fun getArtifactName(artifact: com.android.build.gradle.internal.dsl.DefaultConfig): String {
    val date = DateTimeFormatter.ofPattern("MMM-dd-yyyy").format(LocalDate.now()).toString()
    return project.name + "-v" + "(" + artifact.versionName + ")" + "-code" + "(" + artifact.versionCode + ")" + "-" + date
}