package com.example.tenantmanagementsystem.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystem.R
import com.example.tenantmanagementsystem.data.repository.TenantRepository
import com.example.tenantmanagementsystem.databinding.FragmentDashboardBinding

/**
 * Dashboard screen (Stage 1).
 * Reads directly from the in-memory repository.
 * Stage 4 will switch this to the ViewModel.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshStats()

        binding.btnAddTenant.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_addTenant)
        }

        binding.btnViewTenants.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_viewTenants)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshStats()
    }

    private fun refreshStats() {
        val list = TenantRepository.tenants
        binding.tvTenantCount.text = "Tenants\n${list.size}"
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        binding.tvTotalRent.text = "Total rent\nKSh %,.0f".format(total)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
