package com.gabriel.wayairlinesapp.presenter.status.tabs.all

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
class AllFlightsViewModel@Inject constructor(
    private val getFlightUseCase: GetFlightUseCase
): ViewModel() {

    fun getFlights() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            // Log de depuração antes da chamada para obter os voos
            Log.d("All", "Iniciando chamada para obter os voos.")

            val flights = getFlightUseCase()

            // Log de sucesso
            Log.d("All", "Voos obtidos com sucesso: $flights")

            emit(StateView.Success(data = flights))

        } catch (ex: HttpException) {
            // Log de erro específico para HttpException
            Log.e("All", "Erro HTTP ao tentar obter voos: ${ex.message()}", ex)

            ex.printStackTrace()
            emit(StateView.Error(message = ex.message()))
        } catch (ex: Exception) {
            // Log de erro genérico para qualquer outra exceção
            Log.e("All", "Erro inesperado ao tentar obter voos: ${ex.message}", ex)

            ex.printStackTrace()
            emit(StateView.Error(message = ex.message))
        }
    }

}