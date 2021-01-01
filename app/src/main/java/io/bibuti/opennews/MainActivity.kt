package io.bibuti.opennews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity will serve as the the Activity over which the other UI components will
 * render it-self (This follows the Navigation Architecture)
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}