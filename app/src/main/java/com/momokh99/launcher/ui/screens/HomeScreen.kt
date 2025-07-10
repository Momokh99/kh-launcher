@Composable
fun HomeScreen(viewModel: LauncherViewModel) {
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
        
        AppGrid(
            apps = apps,
            onAppClick = { app -> viewModel.launchApp(app) }
        )
    }
}
