package com.gabriel.wayairlinesapp.presenter.status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.wayairlinesapp.R
import com.gabriel.wayairlinesapp.databinding.FragmentFlightStatusBinding
import com.gabriel.wayairlinesapp.presenter.adapter.ViewPagerAdapter
import com.gabriel.wayairlinesapp.presenter.status.tabs.AllFlightsFragment
import com.gabriel.wayairlinesapp.presenter.status.tabs.CancelledFlightsFragment
import com.gabriel.wayairlinesapp.presenter.status.tabs.CompletedFlightsFragment
import com.gabriel.wayairlinesapp.presenter.status.tabs.FlightsTravelFragment
import com.gabriel.wayairlinesapp.presenter.status.tabs.PerformFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightStatusFragment : Fragment() {

    private var _binding: FragmentFlightStatusBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initTabs()
    }

    private fun initTabs() {
        val pageAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = pageAdapter

        pageAdapter.addFragment(AllFlightsFragment(), R.string.status_all_flights)
        pageAdapter.addFragment(CompletedFlightsFragment(), R.string.status_completed_flights)
        pageAdapter.addFragment(CancelledFlightsFragment(), R.string.status_flight_cancelled)
        pageAdapter.addFragment(FlightsTravelFragment(), R.string.status_open_flights)
        pageAdapter.addFragment(PerformFragment(), R.string.status_flights_in_progress)


        binding.viewPager.offscreenPageLimit = pageAdapter.itemCount

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()
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