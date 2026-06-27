package com.example.globalsurvey

package com.yourname.globalpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.yourname.globalpulse.ui.navigation.AppNavigation
import com.yourname.globalpulse.ui.theme.GlobalPulseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlobalPulseTheme {
                AppNavigation()
            }
        }
    }
}}