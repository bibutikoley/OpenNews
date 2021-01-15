import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Versions {
    //project level..
    const val kotlin = "1.4.21"
    const val gradle = "4.1.1"

    //app level..
    const val hilt = "2.30.1-alpha"
    const val materialDesign = "1.2.1"
    const val coreKtx = "1.3.2"
    const val appCompact = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val desugaring = "1.1.1"
    const val timber = "4.7.1"
    const val fragmentKtx = "2.3.2"
    const val lifecycleKtx = "2.2.0"
    const val retrofit = "2.9.0"
    const val interceptor = "4.9.0"
    const val coroutines = "1.4.2"
    const val room = "2.2.6"
    const val glide = "4.11.0"
}


object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val daggerHiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

object androidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Desugaring {
    const val coreLibraryDesugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
}

object Timber {
    const val logging = "com.jakewharton.timber:timber:${Versions.timber}"
}

object NavigationKtx {
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.fragmentKtx}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.fragmentKtx}"
}

object LifecycleKtx {
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonRetrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Testing {
    const val jUnit = "junit:junit:4.13"
    const val extJUnit = "androidx.test.ext:junit:1.1.1"
    const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
}

object Room {
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
}

object Glide {
    const val lib = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Hilt {
    //dependency injection..
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val androidxHiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"
    const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha02"
}

object DateProducer {
    fun getDate() = DateTimeFormatter.ofPattern("MMM-dd-yyyy").format(LocalDate.now()).toString()
}