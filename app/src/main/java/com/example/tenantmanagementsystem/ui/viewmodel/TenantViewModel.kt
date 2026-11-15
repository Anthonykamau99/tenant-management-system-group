package com.example.tenantmanagementsystem.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystem.data.local.TenantDatabase
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import kotlinx.coroutines.launch

/**
 * ViewModel (Stage 5) — now backed by Room through the repository.
 * Inserts run inside viewModelScope.launch so the UI never freezes.
 */
class TenantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TenantRepository

    val tenants: LiveData<List<Tenant>>
    val tenantCountText: LiveData<String>
    val totalRentText: LiveData<String>
    val allTenantsText: LiveData<String>

    init {
        val dao = TenantDatabase.getDatabase(application).tenantDao()
        repository = TenantRepository(dao)
        tenants = repository.allTenants

        tenantCountText = tenants.map { list ->
            "Tenants\n${list.size}"
        }

        totalRentText = tenants.map { list ->
            val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
            "Total rent\nKSh %,.0f".format(total)
        }

        allTenantsText = tenants.map { list ->
            if (list.isEmpty()) {
                "No tenants yet. Add one from the Dashboard."
            } else {
                list.joinToString("\n\n") { it.summary() }
            }
        }

        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)

        // Save in the background; Room will update LiveData automatically
        viewModelScope.launch {
            repository.addTenant(tenant)
        }

        return tenant
    }

    fun deleteTenant(id: Int) {
        viewModelScope.launch {
            repository.deleteTenant(id)
        }
    }

    // Keep name-based delete for older call sites during transition
    fun deleteTenant(name: String) {
        viewModelScope.launch {
            val list = tenants.value.orEmpty()
            val match = list.find { it.name == name }
            if (match != null) {
                repository.deleteTenant(match.id)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}
