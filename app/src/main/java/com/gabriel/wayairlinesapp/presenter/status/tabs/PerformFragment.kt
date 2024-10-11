package com.gabriel.wayairlinesapp.presenter.status.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.gabriel.wayairlinesapp.databinding.FragmentPerformBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.presenter.adapter.FlightAdapter
import com.gabriel.wayairlinesapp.presenter.status.FlightStatusViewModel
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerformFragment : Fragment() {

    private val viewModel: FlightStatusViewModel by viewModels()

    private var _binding: FragmentPerformBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFlights()

    }

    private fun getFlights() {
        viewModel.getFlights().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
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
        with(binding.rvFlights) {
            setHasFixedSize(true)
            adapter = FlightAdapter(flights) { flightId ->
                val action =
                    PerformFragmentDirections.actionPerformFragmentToFlightDetailsFragment(
                        flightId
                    )
                findNavController().navigate(action)
            }

        }
    }
}