package com.gabriel.wayairlinesapp.presenter.status

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.wayairlinesapp.domain.usecase.GetFlightUseCase
import com.gabriel.wayairlinesapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FlightStatusViewModel@Inject constructor(
    private val getFlightUseCase: GetFlightUseCase
): ViewModel() {

    fun getFlights() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val flights = getFlightUseCase()

            emit(StateView.Success(data = flights))

        } catch (ex: HttpException) {


            ex.printStackTrace()
            emit(StateView.Error(message = ex.message()))
        } catch (ex: Exception) {


            ex.printStackTrace()
            emit(StateView.Error(message = ex.message))
        }
    }
}