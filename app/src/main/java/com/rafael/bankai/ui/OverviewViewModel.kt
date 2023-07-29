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

    private val _charactersStatus = MutableLiveData<BankaiApiStatus>()
    val characterStatus: LiveData<BankaiApiStatus> = _charactersStatus

    private val _quoteStatus = MutableLiveData<BankaiApiStatus>()
    val quoteStatus: LiveData<BankaiApiStatus> = _quoteStatus

    private val _information = MutableLiveData<BleachData>()
    val information: LiveData<BleachData> = _information

    private val _characters = MutableLiveData<CharactersData>()
    val characters: LiveData<CharactersData> = _characters

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    init {
        getInfo()
        getCharacters()
        getQuote()
    }

    /**
     * Function to get concatenated genre names
     */
    fun getGenreNames(): String {
        val genres = information.value?.data?.genres
        return genres?.joinToString(", ") { it.name } ?: ""
    }

    /**
     * Function to get the name of the first producer
     */
    fun getFirstProducerName(): String {
        val producers = information.value?.data?.producers
        return producers?.firstOrNull()?.name ?: ""
    }

    /**
     * Function to get the name of the first licensor
     */
    fun getFirstLicensorName(): String {
        val licensors = information.value?.data?.licensors
        return licensors?.firstOrNull()?.name ?: ""
    }

    /**
     * Function to search characters given a query
     */
    fun searchCharacters(query: String): List<Character> {
        val allCharacters = characters.value!!.data
        val filteredCharacters = allCharacters.filter { character ->
            character.character.name.contains(query, ignoreCase = true)
        }
        return filteredCharacters
    }

    private fun getInfo() {
        viewModelScope.launch {
            _status.value = BankaiApiStatus.LOADING
            try {
                _information.value = ApiService.jikanApiService.getInformation()
                _status.value = BankaiApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BankaiApiStatus.ERROR
            }
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _charactersStatus.value = BankaiApiStatus.LOADING
            try {
                _characters.value = ApiService.jikanApiService.getCharacters()
                _charactersStatus.value = BankaiApiStatus.DONE
            } catch (e: Exception) {
                _charactersStatus.value = BankaiApiStatus.ERROR
            }
        }
    }

    private fun getQuote() {
        viewModelScope.launch {
            _quoteStatus.value = BankaiApiStatus.LOADING
            try {
                _quote.value = ApiService.animeChanApiService.getQuote()
                _quoteStatus.value = BankaiApiStatus.DONE
            } catch (e: Exception) {
                _quoteStatus.value = BankaiApiStatus.ERROR
            }
        }
    }
}