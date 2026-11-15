package com.example.tenantmanagementsystem.ui.tenants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.databinding.ItemTenantBinding

class TenantAdapter(
    private val onCallClick: (Tenant) -> Unit,
    private val onDeleteClick: (Tenant) -> Unit
) : ListAdapter<Tenant, TenantAdapter.TenantViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val binding = ItemTenantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TenantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TenantViewHolder(
        private val binding: ItemTenantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tenant: Tenant) {
            binding.tvName.text = tenant.name
            binding.tvPhone.text = tenant.phone
            binding.tvRent.text = "KSh ${tenant.rent}"

            binding.btnCall.setOnClickListener { onCallClick(tenant) }
            binding.btnDelete.setOnClickListener { onDeleteClick(tenant) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Tenant>() {
            override fun areItemsTheSame(oldItem: Tenant, newItem: Tenant): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tenant, newItem: Tenant): Boolean {
                return oldItem == newItem
            }
        }
    }
}
