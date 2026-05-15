package com.example.maguilarmusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.maguilarmusicapp.ui.components.MiniPlayer
import com.example.maguilarmusicapp.ui.screens.HomeScreen
import com.example.maguilarmusicapp.ui.theme.MAguilarMusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAguilarMusicAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        HomeScreen()
                        MiniPlayer(modifier = Modifier.align(Alignment.BottomCenter))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MAguilarMusicAppTheme {
        Scaffold { padding ->
            Box(modifier = Modifier.padding(padding)) {
                HomeScreen()
                MiniPlayer(modifier = Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}
