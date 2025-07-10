
package com.momokh99.launcher.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.momokh99.launcher.ui.components.AppGrid
import com.momokh99.launcher.ui.components.BranchCard
import com.momokh99.launcher.ui.components.SearchBar
import com.momokh99.launcher.viewmodel.LauncherViewModel
@Composable
fun HomeScreen(
    viewModel: LauncherViewModel,
    onBranchClick: (String) -> Unit
) {
        val apps by viewModel.apps.collectAsStateWithLifecycle()
    val branches by viewModel.branches.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            onSearch = { viewModel.updateSearchQuery(it) },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Branches",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Branch Section
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(branches.values.toList()) { branch ->
                BranchCard(
                    branch = branch,
                    onClick = { onBranchClick(branch.name) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "All Apps",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AppGrid(
            apps = apps,
            onAppClick = { app -> viewModel.launchApp(app) }
        )
    }
}
