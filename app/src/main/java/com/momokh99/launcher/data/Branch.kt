package com.momokh99.launcher.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Branch(
    val name: String,
    val apps: List<AppInfo>,
    val color: Color,
    val icon: ImageVector? = null
)
package com.momokh99.launcher.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Branch(
    val name: String,
    val apps: List<AppInfo> = emptyList(),
    val icon: ImageVector? = Icons.Default.Apps,
    val color: Color = Color.Blue
)
