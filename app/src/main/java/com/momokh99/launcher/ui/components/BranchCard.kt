package com.momokh99.launcher.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.momokh99.launcher.data.Branch

@Composable
fun BranchCard(
    branch: Branch,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                branch.icon?.let { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = branch.name,
                        tint = branch.color,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(
                    text = branch.name,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
