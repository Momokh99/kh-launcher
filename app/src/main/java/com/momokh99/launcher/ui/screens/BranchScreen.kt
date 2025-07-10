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
        
        // Branch Card
        BranchCard(
            branch = branch,
            onClick = { /* Branch settings */ }
        )
        
        // Apps in Branch
        AppGrid(
            apps = branch.apps,
            onAppClick = { app -> viewModel.launchApp(app) }
        )
    }
}
