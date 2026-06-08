package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Mascota
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class SolicitudItem(
    val mascota: Mascota
)

@HiltViewModel
class SolicitudViewModel @Inject constructor() : ViewModel() {

    private val _solicitudes =
        MutableStateFlow<List<SolicitudItem>>(emptyList())

    val solicitudes: StateFlow<List<SolicitudItem>> =
        _solicitudes.asStateFlow()

    val totalSolicitudes: StateFlow<Int> =
        _solicitudes
            .map { it.size }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                0
            )

    fun agregarSolicitud(mascota: Mascota) {

        val existe =
            _solicitudes.value.any {
                it.mascota.id == mascota.id
            }

        if (!existe) {
            _solicitudes.update {
                it + SolicitudItem(mascota)
            }
        }
    }

    fun eliminarSolicitud(mascotaId: Int) {
        _solicitudes.update { lista ->
            lista.filter {
                it.mascota.id != mascotaId
            }
        }
    }

    fun limpiarSolicitudes() {
        _solicitudes.value = emptyList()
    }
}