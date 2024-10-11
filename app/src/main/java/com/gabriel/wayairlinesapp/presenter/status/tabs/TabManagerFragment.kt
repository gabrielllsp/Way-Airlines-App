package com.gabriel.wayairlinesapp.presenter.status.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.gabriel.wayairlinesapp.R
import com.gabriel.wayairlinesapp.databinding.FragmentTabManagerBinding
import com.gabriel.wayairlinesapp.domain.model.Flight
import com.gabriel.wayairlinesapp.presenter.adapter.FlightAdapter
import com.gabriel.wayairlinesapp.presenter.adapter.TabManagerAdapter
import com.gabriel.wayairlinesapp.presenter.adapter.ViewPagerAdapter
import com.gabriel.wayairlinesapp.presenter.home.HomeFragmentDirections
import com.gabriel.wayairlinesapp.presenter.home.HomeViewModel
import com.gabriel.wayairlinesapp.util.StateView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TabManagerFragment : Fragment() {

    private var _binding: FragmentTabManagerBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabManagerBinding.inflate(inflater, container, false)
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
        pageAdapter.addFragment(FlightsCancelledFragment(), R.string.status_flight_cancelled)
        pageAdapter.addFragment(FlightsInProgressFragment(), R.string.status_flights_in_progress)
        pageAdapter.addFragment(OpenFlightsFragment(), R.string.status_open_flights)

        binding.viewPager.offscreenPageLimit = 5

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
            binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

//                    applyFilter(position)
                }
            })
        }.attach()
    }


//    private fun initRecyclerView(flights: List<Flight>) {
//        with(binding.rvFlights){
//            setHasFixedSize(true)
//            adapter = TabManagerAdapter(flights){flightId ->
//                val action = TabManagerFragmentDirections.actionTabManagerFragmentToFlightDetailsFragment(flightId)
//                findNavController().navigate(action)
//            }
//        }
//    }


    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_tabManagerFragment_to_homeFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}