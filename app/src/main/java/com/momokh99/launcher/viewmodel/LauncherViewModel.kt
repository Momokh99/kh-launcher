package com.momokh99.launcher.viewmodel

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.momokh99.launcher.data.AppInfo
import com.momokh99.launcher.data.Branch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LauncherViewModel(application: Application) : AndroidViewModel(application) {
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps = _apps.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _branches = MutableStateFlow<Map<String, Branch>>(emptyMap())
    val branches = _branches.asStateFlow()

    init {
        loadApps()
        initializeBranches()
    }

    private fun loadApps() {
        viewModelScope.launch {
            val pm = getApplication<Application>().packageManager
            val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
                .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
                .map { appInfo ->
                    AppInfo(
                        name = pm.getApplicationLabel(appInfo).toString(),
                        packageName = appInfo.packageName,
                        icon = appInfo.loadIcon(pm)
                    )
                }
                .sortedBy { it.name }
            _apps.value = apps
        }
    }

    private fun initializeBranches() {
        val defaultBranches = mapOf(
            "Work" to Branch("Work", emptyList()),
            "Games" to Branch("Games", emptyList()),
            "Social" to Branch("Social", emptyList()),
            "Utilities" to Branch("Utilities", emptyList())
        )
        _branches.value = defaultBranches
    }

    fun getBranch(branchName: String) = MutableStateFlow(
        _branches.value[branchName] ?: Branch("Unknown", emptyList())
    ).asStateFlow()

    fun launchApp(appInfo: AppInfo) {
        val pm = getApplication<Application>().packageManager
        val intent = pm.getLaunchIntentForPackage(appInfo.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent?.let { getApplication<Application>().startActivity(it) }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
