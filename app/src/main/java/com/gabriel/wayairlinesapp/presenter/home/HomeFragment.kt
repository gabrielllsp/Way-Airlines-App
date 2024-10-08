package com.gabriel.wayairlinesapp.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gabriel.wayairlinesapp.databinding.FragmentHomeBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                    binding.progressBar.isInvisible = true
                }
            }
        }
    }

    private fun initRecyclerView(flights: List<Flight>) {
        with(binding.rvFlights){
            setHasFixedSize(true)
            adapter = FlightAdapter(flights){
//                val action = HomeFragmentDirections.actionHomeFragmentToFlightDetailsFragment(it)
//                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}