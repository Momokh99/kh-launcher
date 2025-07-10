package com.momokh99.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.momokh99.launcher.ui.theme.LauncherTheme
import com.momokh99.launcher.viewmodel.LauncherViewModel
import com.momokh99.launcher.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherTheme {
                HomeScreen(viewModel)
            }
        }
    }
}
