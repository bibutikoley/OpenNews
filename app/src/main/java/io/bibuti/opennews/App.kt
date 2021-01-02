package io.bibuti.opennews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * This application file is very important
 * It acts as the hilt entry point and also is triggered every time the app is started
 */
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var appInstance: App
    }

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return super.createStackElementTag(element) + " || Line No. -> " + element.lineNumber
            }
        })
    }

}