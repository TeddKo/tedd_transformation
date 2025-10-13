package com.tedd.tedd_transform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.tedd.tedd_transform.extensions.addFocusCleaner
import com.tedd.tedd_transform.ui.TransformControllerScreen
import com.tedd.tedd_transform.ui.theme.Tedd_transformTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val focusManager = LocalFocusManager.current
            Tedd_transformTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .addFocusCleaner(focusManager = focusManager)
                ) { innerPadding ->
                    TransformControllerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}