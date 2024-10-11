package com.gabriel.wayairlinesapp.presenter.status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabriel.wayairlinesapp.R
import com.gabriel.wayairlinesapp.databinding.FragmentFlightStatusBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.presenter.adapter.FlightAdapter
import com.gabriel.wayairlinesapp.presenter.details.FlightDetailsFragmentArgs
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightStatusFragment : Fragment() {

    private var _binding: FragmentFlightStatusBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FlightStatusViewModel by viewModels()
//    private val args: FlightDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFlights()
        initListeners()
    }

    private fun getFlights() {
        viewModel.getFlights().observe(viewLifecycleOwner){ stateView ->
            when(stateView){
                is StateView.Error -> {}
                is StateView.Success -> {

                    val flights = stateView.data ?: emptyList()
                    initRecyclerView(flights)
                }
                is StateView.Loading -> {
                    binding.progressBar.isInvisible = true
                }
            }
        }
    }

    private fun initRecyclerView(flights: List<Flight>) {
        with(binding.rvFlights){
            setHasFixedSize(true)
            adapter = FlightAdapter(flights){flightId ->
                val action = FlightStatusFragmentDirections.actionFlightStatusFragmentToFlightDetailsFragment(flightId)
                findNavController().navigate(action)
            }
        }
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_flightStatusFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}