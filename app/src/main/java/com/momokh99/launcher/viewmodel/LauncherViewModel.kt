package com.momokh99.launcher.viewmodel

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.momokh99.launcher.data.AppInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LauncherViewModel(application: Application) : AndroidViewModel(application) {
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps = _apps.asStateFlow()

    init {
        loadApps()
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

    fun launchApp(appInfo: AppInfo) {
        val pm = getApplication<Application>().packageManager
        val intent = pm.getLaunchIntentForPackage(appInfo.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent?.let { getApplication<Application>().startActivity(it) }
    }
}
private val _searchQuery = MutableStateFlow("")
val searchQuery = _searchQuery.asStateFlow()

private val _filteredApps = MutableStateFlow<List<AppInfo>>(emptyList())
val filteredApps = _filteredApps.asStateFlow()

fun updateSearchQuery(query: String) {
    _searchQuery.value = query
    filterApps()
}

private fun filterApps() {
    viewModelScope.launch {
        _filteredApps.value = _apps.value.filter { app ->
            app.name.contains(_searchQuery.value, ignoreCase = true)
        }
    }
}

private val _branches = MutableStateFlow<Map<String, Branch>>(emptyMap())
val branches = _branches.asStateFlow()

fun getBranch(name: String) = flow {
    emit(_branches.value[name] ?: Branch(
        name = name,
        apps = emptyList(),
        color = MaterialTheme.colorScheme.primary
    ))
}

fun addAppToBranch(app: AppInfo, branchName: String) {
    viewModelScope.launch {
        val branch = _branches.value[branchName]
        if (branch != null) {
            val updatedBranch = branch.copy(
                apps = branch.apps + app
            )
            _branches.value = _branches.value + (branchName to updatedBranch)
        }
    }
}

