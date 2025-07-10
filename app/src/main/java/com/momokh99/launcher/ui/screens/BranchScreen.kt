
package com.momokh99.launcher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momokh99.launcher.ui.components.AppGrid
import com.momokh99.launcher.ui.components.BranchCard
import com.momokh99.launcher.viewmodel.LauncherViewModel

@Composable
fun BranchScreen(
    viewModel: LauncherViewModel,
    branchName: String,
    onNavigateBack: () -> Unit
) {
    val branch by viewModel.getBranch(branchName).collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = branch.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
           Spacer(modifier = Modifier.width(48.dp)) // Balance the back button
        }
        
        // Branch Card
        BranchCard(
            branch = branch,
            onClick = { /* Branch settings */ }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Apps in Branch
        if (branch.apps.isNotEmpty()) {
            AppGrid(
                apps = branch.apps,
                onAppClick = { app -> viewModel.launchApp(app) }
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No apps in this branch yet",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        
    }
}
