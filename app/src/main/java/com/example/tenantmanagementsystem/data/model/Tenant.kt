package com.example.tenantmanagementsystem.data.model

import java.io.Serializable

/**
 * Simple data class for a tenant.
 * (Room annotations are added in Stage 3)
 */
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String,
    val id: Int = 0
) : Serializable {

    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}
