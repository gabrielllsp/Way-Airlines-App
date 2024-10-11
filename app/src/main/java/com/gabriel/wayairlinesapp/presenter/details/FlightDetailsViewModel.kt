package com.gabriel.wayairlinesapp.presenter.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.wayairlinesapp.domain.usecase.GetFlightByIdUseCase
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FlightDetailsViewModel @Inject constructor(
    private val getFlightByIdUseCase: GetFlightByIdUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "FlightDetailsViewModel"
    }

    fun getFlightsById(flightId: Int) = liveData(Dispatchers.IO) {
        Log.d(TAG, "Fetching flight with ID: $flightId") // Log when fetching starts
        try {
            emit(StateView.Loading())

            val flight = getFlightByIdUseCase(flightId)

            emit(StateView.Success(flight))

        } catch (ex: HttpException) {
            ex.printStackTrace()

            emit(StateView.Error(message = ex.message()))

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(message = ex.message))
        }
    }
}