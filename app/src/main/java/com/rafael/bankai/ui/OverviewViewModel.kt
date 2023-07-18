package com.rafael.bankai.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafael.bankai.network.*
import kotlinx.coroutines.launch

enum class BankaiApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<BankaiApiStatus>()
    val status: LiveData<BankaiApiStatus> = _status

    private val _information = MutableLiveData<BleachData>()
    val information: LiveData<BleachData> = _information

    private val _characters = MutableLiveData<CharactersData>()
    val characters: LiveData<CharactersData> = _characters

    init {
        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch {
            _status.value = BankaiApiStatus.LOADING
            try {
                _information.value = ApiService.jikanApiService.getInformation()
                _characters.value = ApiService.jikanApiService.getCharacters()
                _status.value = BankaiApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BankaiApiStatus.ERROR
            }
        }
    }
}