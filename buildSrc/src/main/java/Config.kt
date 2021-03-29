object Versions {
    //project level..
    const val kotlin = "1.4.31"
    const val gradle = "4.1.2"

    //app level..
    const val materialDesign = "1.3.0"
    const val coreKtx = "1.3.2"
    const val appCompact = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val desugaring = "1.1.5"
    const val timber = "4.7.1"
    const val fragmentKtx = "2.3.3"
    const val lifecycleKtx = "2.3.0"
    const val retrofit = "2.9.0"
    const val interceptor = "4.9.0"
    const val coroutines = "1.4.2"
    const val room = "2.2.6"
    const val paging = "2.1.2"
    const val glide = "4.12.0"
}


object Jetbrains {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
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

object Paging {
    const val runtimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}

object Glide {
    const val lib = "com.github.bumptech.glide:glide:${Versions.glide}"
}