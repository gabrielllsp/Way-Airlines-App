package com.gabriel.wayairlinesapp.presenter.status.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.gabriel.wayairlinesapp.R
import com.gabriel.wayairlinesapp.databinding.FragmentAllFlightsBinding
import com.gabriel.wayairlinesapp.databinding.FragmentTabManagerBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.presenter.adapter.FlightAdapter
import com.gabriel.wayairlinesapp.presenter.adapter.TabManagerAdapter
import com.gabriel.wayairlinesapp.presenter.home.HomeFragmentDirections
import com.gabriel.wayairlinesapp.presenter.home.HomeViewModel
import com.gabriel.wayairlinesapp.presenter.status.tabs.all.AllFlightsViewModel
import com.gabriel.wayairlinesapp.util.StateView


class AllFlightsFragment : Fragment() {

    private var _binding: FragmentAllFlightsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllFlightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFlights()
    }

    private fun getFlights() {
        viewModel.getFlights().observe(viewLifecycleOwner){stateView ->
            when(stateView){
                is StateView.Error -> {}
                is StateView.Success -> {

                    val flights = stateView.data ?: emptyList()
                    initRecyclerView(flights)
                }
                is StateView.Loading -> {
//                    binding.progressBar.isInvisible = true
                }
            }
        }
    }

    private fun initRecyclerView(flights: List<Flight>) {
        with(binding.rvFlights){
            setHasFixedSize(true)
            adapter = FlightAdapter(flights){flightId ->
                val action = HomeFragmentDirections.actionHomeFragmentToFlightDetailsFragment(flightId = flightId )
                findNavController().navigate(action)
            }
        }
    }
}