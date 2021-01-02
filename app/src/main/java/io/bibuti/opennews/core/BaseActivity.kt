package io.bibuti.opennews.core

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * All activities in the app should be inherited from this file.
 */
open class BaseActivity : AppCompatActivity() {

    /**
     *  Can initialize/declare anything here which is to used across all the child activities
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Only portrait mode
    }
}