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
        create("staging") {
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

    implementation(Jetbrains.kotlinStdLib)

    implementation(androidX.coreKtx)
    implementation(androidX.appcompat)
    implementation(androidX.materialDesign)
    implementation(androidX.constraintLayout)

    //de-sugaring for using latest java 8 features on older devices
    coreLibraryDesugaring(Desugaring.coreLibraryDesugaring)

    //logging with timber
    implementation(Timber.logging)

    //navigation..
    implementation(NavigationKtx.navigationFragmentKtx)
    implementation(NavigationKtx.navigationUIKtx)

    //lifecycle..
    implementation(LifecycleKtx.liveDataKtx)
    implementation(LifecycleKtx.viewModelKtx)

    //coroutines..
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    //retrofit..
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonRetrofitConverter)
    implementation(Retrofit.interceptor)

    //database..
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)

    //paging..
    implementation(Paging.runtimeKtx)

    //glide..
    implementation(Glide.lib)

    //dependency injection..
    val hiltVersion = "2.33-beta"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0-beta01")

    //chucker intercepter
    debugImplementation("com.github.chuckerteam.chucker:library:3.4.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.4.0")


    //testing..
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)

}

fun getArtifactName(artifact: com.android.build.gradle.internal.dsl.DefaultConfig): String {
    val date = DateTimeFormatter.ofPattern("MMM-dd-yyyy").format(LocalDate.now()).toString()
    return project.name + "-v" + "(" + artifact.versionName + ")" + "-code" + "(" + artifact.versionCode + ")" + "-" + date
}