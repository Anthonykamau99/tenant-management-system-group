package com.example.tenantmanagementsystem.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystem.data.local.TenantDao
import com.example.tenantmanagementsystem.data.model.Tenant

/**
 * Repository (Stage 5) — single source of truth backed by Room.
 * ViewModels talk to the repository; the repository talks to the DAO.
 */
class TenantRepository(private val tenantDao: TenantDao) {

    val allTenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insert(tenant)
    }

    suspend fun getTenantById(id: Int): Tenant? {
        return tenantDao.getTenantById(id)
    }

    suspend fun deleteTenant(id: Int) {
        tenantDao.deleteById(id)
    }

    suspend fun deleteAll() {
        tenantDao.deleteAll()
    }
}
