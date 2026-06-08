package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Fundacion
import com.adopcion.domain.model.FundacionPayload
import com.adopcion.domain.repository.FundacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundacionViewModel @Inject constructor(
    private val repository: FundacionRepository
) : ViewModel() {

    private val _fundaciones = MutableStateFlow<List<Fundacion>>(emptyList())
    val fundaciones: StateFlow<List<Fundacion>> = _fundaciones.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadFundaciones() {
        viewModelScope.launch {
            _loading.value = true

            repository.getFundaciones()
                .onSuccess {
                    _fundaciones.value = it
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message
                }

            _loading.value = false
        }
    }

    fun crearFundacion(payload: FundacionPayload) {
        viewModelScope.launch {
            repository.createFundacion(payload)
                .onSuccess {
                    loadFundaciones()
                }
                .onFailure {
                    _error.value = it.message ?: "Error al crear fundación"
                }
        }
    }

    fun eliminarFundacion(id: Int) {
        viewModelScope.launch {
            repository.deleteFundacion(id)
                .onSuccess {
                    loadFundaciones()
                }
                .onFailure {
                    _error.value = it.message ?: "Error al eliminar fundación"
                }
        }
    }
}