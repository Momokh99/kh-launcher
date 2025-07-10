@Composable
fun HomeScreen(
    viewModel: LauncherViewModel,
    onBranchClick: (String) -> Unit
) {
    val apps by viewModel.apps.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            onSearch = { /* TODO: Implement search */ },
            modifier = Modifier.fillMaxWidth()
        )
        
        // Add Branch Section
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(viewModel.branches.value.toList()) { (name, branch) ->
                BranchCard(
                    branch = branch,
                    onClick = { onBranchClick(name) }
                )
            }
        }
        
        AppGrid(
            apps = apps,
            onAppClick = { app -> viewModel.launchApp(app) }
        )
    }
}
