package com.example.tenantmanagementsystem.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding

/**
 * Lists all tenants from the in-memory repository (Stage 1).
 * Stage 4 will switch this to the ViewModel.
 */
class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TenantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TenantAdapter(
            onCallClick = { tenant -> callTenant(tenant) },
            onDeleteClick = { tenant ->
                TenantRepository.deleteTenant(tenant.name)
                refreshList()
                Toast.makeText(requireContext(), "Deleted ${tenant.name}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        refreshList()
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        val list = TenantRepository.tenants
        adapter.submitList(list)
        binding.tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun callTenant(tenant: Tenant) {
        val phone = tenant.phone.trim()
        if (phone.isEmpty()) {
            Toast.makeText(requireContext(), "No phone number", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
