package com.example.nit3213finalapp.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213finalapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    // getting viewmodel
    private val viewModel: DashboardViewModel by viewModels()

    // adapter for recyclerview
    private lateinit var adapter: DashboardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting keypass from args
        val args = DashboardFragmentArgs.fromBundle(requireArguments())
        val keypass = args.keypass

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // adapter with click event
        adapter = DashboardAdapter(emptyList()) { selectedItem ->
            val action = DashboardFragmentDirections.actionDashboardFragmentToDetailsFragment(selectedItem)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // observing livedata to update ui
        viewModel.entities.observe(viewLifecycleOwner) { list ->
            adapter.setData(list)
        }

        // calling api to get data
        viewModel.getDashboardData(keypass)
    }
}
