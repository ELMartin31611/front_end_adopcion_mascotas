package com.adopcion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adopcion.domain.model.Donacion
import com.adopcion.domain.repository.DonacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DonacionViewModel @Inject constructor(
    private val repository: DonacionRepository
) : ViewModel() {

    private val _donaciones =
        MutableStateFlow<List<Donacion>>(emptyList())

    val donaciones: StateFlow<List<Donacion>> =
        _donaciones.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadDonaciones() {
        viewModelScope.launch {

            _loading.value = true

            repository.getDonaciones()
                .onSuccess {
                    _donaciones.value = it
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message
                }

            _loading.value = false
        }
    }
}