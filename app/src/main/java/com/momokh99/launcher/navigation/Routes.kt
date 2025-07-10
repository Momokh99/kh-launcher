package com.momokh99.launcher.navigation

object Routes {
    const val HOME = "home"
    const val BRANCH = "branch/{branchName}"
    const val SEARCH = "search"
    const val SETTINGS = "settings"
    
    fun getBranchRoute(branchName: String) = "branch/$branchName"
}
