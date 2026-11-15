package com.example.tenantmanagementsystem.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository

/**
 * ViewModel (UI layer) — Stage 2.
 * Holds LiveData and transformations. Screens will connect to it in Stage 4.
 *
 * - PRIVATE MutableLiveData: only this ViewModel can change it
 * - PUBLIC LiveData: screens can watch it, not change it
 * - map() creates derived LiveData that updates automatically
 * - No Toast, no View, no Context — ViewModel must never hold a screen
 */
class TenantViewModel : ViewModel() {

    // 1. THE DATA
    private val _tenants = MutableLiveData<List<Tenant>>(TenantRepository.tenants.toList())
    val tenants: LiveData<List<Tenant>> = _tenants

    // 2. TRANSFORMATIONS — text ready for the screens
    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) "No tenants yet. Add one from the Dashboard."
        else list.joinToString("\n\n") { it.summary() }
    }

    // 3. ACTIONS the screens can ask for
    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)
        TenantRepository.addTenant(tenant)
        _tenants.value = TenantRepository.tenants.toList()
        return tenant
    }

    fun deleteTenant(name: String) {
        TenantRepository.deleteTenant(name)
        _tenants.value = TenantRepository.tenants.toList()
    }

    // 4. LIFECYCLE LOGS
    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}
