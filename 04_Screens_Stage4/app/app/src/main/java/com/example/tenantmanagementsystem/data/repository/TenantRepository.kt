package com.example.tenantmanagementsystem.data.repository

import com.example.tenantmanagementsystem.data.model.Tenant

/**
 * In-memory repository (Stage 1 / early stages).
 * Data is lost when the app is closed.
 * Stage 5 will replace this with Room.
 */
object TenantRepository {

    private val _tenants = mutableListOf<Tenant>()

    val tenants: List<Tenant>
        get() = _tenants.toList()

    fun addTenant(tenant: Tenant) {
        _tenants.add(tenant)
    }

    fun deleteTenant(name: String) {
        _tenants.removeAll { it.name == name }
    }

    fun clear() {
        _tenants.clear()
    }
}
