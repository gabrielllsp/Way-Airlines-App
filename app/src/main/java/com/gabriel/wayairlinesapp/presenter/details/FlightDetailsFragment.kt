package com.gabriel.wayairlinesapp.presenter.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabriel.wayairlinesapp.databinding.FragmentFlightDetailsBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightDetailsFragment : Fragment() {

    private var _binding: FragmentFlightDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: FlightDetailsFragmentArgs by navArgs()
    private val viewModel: FlightDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        getFlightsById()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getFlightsById() {
        viewModel.getFlightsById(args.flightId).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {

                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    stateView.data?.let { configData(it) }
                }

                is StateView.Error -> {
                    Toast.makeText(requireContext(), stateView.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun configData(flight: Flight) {
        binding.textAirplaneName.text =flight.airplaneName
        binding.textStatus.text = flight.status
        binding.textCompletionStatus.text = flight.completionStatus
        binding.textDepartureAirport.text = flight.departureAirport
        binding.textArrivalAirport.text = flight.arrivalAirport
        binding.textStartDate.text = flight.startDate
        binding.textEndDate.text = flight.endDate
        binding.textDepartureTime.text = flight.departureTime
        binding.textArrivalTime.text = flight.arrivalTime
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}