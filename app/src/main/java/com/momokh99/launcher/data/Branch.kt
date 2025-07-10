package com.momokh99.launcher.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Branch(
    val name: String,
    val apps: List<AppInfo>,
    val color: Color,
    val icon: ImageVector? = null
)
