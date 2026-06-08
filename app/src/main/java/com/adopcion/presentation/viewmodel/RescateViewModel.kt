package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Rescate
import com.adopcion.domain.model.RescatePayload
import com.adopcion.domain.repository.RescateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RescateViewModel @Inject constructor(
    private val repository: RescateRepository
) : ViewModel() {

    private val _rescates = MutableStateFlow<List<Rescate>>(emptyList())
    val rescates: StateFlow<List<Rescate>> = _rescates.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadRescates() {
        viewModelScope.launch {

            _loading.value = true

            repository.getRescates()
                .onSuccess {
                    _rescates.value = it
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message
                }

            _loading.value = false
        }
    }

    fun crearRescate(payload: RescatePayload) {
        viewModelScope.launch {

            repository.createRescate(payload)
                .onSuccess {
                    loadRescates()
                }
                .onFailure {
                    _error.value =
                        it.message ?: "Error al crear rescate"
                }
        }
    }

    fun eliminarRescate(id: Int) {
        viewModelScope.launch {

            repository.deleteRescate(id)
                .onSuccess {
                    loadRescates()
                }
                .onFailure {
                    _error.value =
                        it.message ?: "Error al eliminar rescate"
                }
        }
    }
}