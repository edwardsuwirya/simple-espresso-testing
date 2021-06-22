package com.enigmacamp.mysimpleespresso.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import kotlinx.coroutines.launch

class SecondActivityViewModel(private val spentRepository: SpentRepository) : ViewModel() {
    private var _spentListLiveData = MutableLiveData<List<Spent>>()
    val spentListLiveData: LiveData<List<Spent>>
        get() = _spentListLiveData


    fun getRecentSpent() {
        viewModelScope.launch {
            val spents = spentRepository.getFirst5()
            _spentListLiveData.postValue(spents)
        }
    }
}