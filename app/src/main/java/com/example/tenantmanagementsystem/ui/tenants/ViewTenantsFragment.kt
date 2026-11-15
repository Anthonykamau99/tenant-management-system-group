package com.example.tenantmanagementsystem.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tenantmanagementsystem.data.model.Tenant
import com.example.tenantmanagementsystem.databinding.FragmentViewTenantsBinding
import com.example.tenantmanagementsystem.ui.viewmodel.TenantViewModel

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TenantViewModel by activityViewModels()
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
                viewModel.deleteTenant(tenant.id)
                Toast.makeText(requireContext(), "Deleted ${tenant.name}", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.tenants.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (list.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun callTenant(tenant: Tenant) {
        val phone = tenant.phone.trim()
        if (phone.isEmpty()) {
            Toast.makeText(requireContext(), "No phone number", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
