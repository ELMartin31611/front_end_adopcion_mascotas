package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Solicitud
import com.adopcion.domain.repository.SolicitudRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SolicitudUiState(
    val loading: Boolean = false,
    val solicitudes: List<Solicitud> = emptyList(),
    val error: String? = null,
)

@HiltViewModel
class SolicitudViewModel @Inject constructor(
    private val repository: SolicitudRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SolicitudUiState())
    val uiState: StateFlow<SolicitudUiState> = _uiState.asStateFlow()

    init {
        cargarSolicitudes()
    }

    fun cargarSolicitudes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)

            repository.getSolicitudes()
                .onSuccess { lista ->
                    _uiState.value = SolicitudUiState(
                        loading = false,
                        solicitudes = lista
                    )
                }
                .onFailure { error ->
                    _uiState.value = SolicitudUiState(
                        loading = false,
                        error = error.message ?: "Error al cargar solicitudes"
                    )
                }
        }
    }

    fun eliminarSolicitud(id: Int) {
        viewModelScope.launch {
            repository.deleteSolicitud(id)
                .onSuccess {
                    cargarSolicitudes()
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        error = error.message ?: "Error al eliminar solicitud"
                    )
                }
        }
    }
}