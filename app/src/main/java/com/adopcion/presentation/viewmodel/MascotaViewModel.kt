package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Mascota
import com.adopcion.domain.model.MascotaPayload
import com.adopcion.domain.repository.MascotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MascotaUiState {
    object Loading : MascotaUiState

    data class Success(
        val mascotas: List<Mascota>
    ) : MascotaUiState

    data class Error(
        val message: String
    ) : MascotaUiState
}

@HiltViewModel
class MascotaViewModel @Inject constructor(
    private val repository: MascotaRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<MascotaUiState>(MascotaUiState.Loading)

    val state: StateFlow<MascotaUiState> =
        _state.asStateFlow()

    fun cargarMascotas() {
        viewModelScope.launch {
            _state.value = MascotaUiState.Loading

            repository.getMascotas()
                .onSuccess {
                    _state.value = MascotaUiState.Success(it)
                }
                .onFailure {
                    _state.value = MascotaUiState.Error(
                        it.message ?: "Error"
                    )
                }
        }
    }

    fun crearMascota(payload: MascotaPayload) {
        viewModelScope.launch {
            repository.createMascota(payload)
                .onSuccess {
                    cargarMascotas()
                }
                .onFailure {
                    _state.value = MascotaUiState.Error(
                        it.message ?: "Error al crear mascota"
                    )
                }
        }
    }

    fun eliminarMascota(id: Int) {
        viewModelScope.launch {
            repository.deleteMascota(id)
                .onSuccess {
                    cargarMascotas()
                }
                .onFailure {
                    _state.value = MascotaUiState.Error(
                        it.message ?: "Error al eliminar mascota"
                    )
                }
        }
    }
}