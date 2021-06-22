package com.enigmacamp.mysimpleespresso.presentation

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

    private var _spentDetailLiveData = MutableLiveData<Spent>()
    val spentDetailLiveData: LiveData<Spent>
        get() = _spentDetailLiveData

    fun getRecentSpent() {
        viewModelScope.launch {
            val spents = spentRepository.getFirst5()
            _spentListLiveData.postValue(spents)
        }
    }

    fun getDetailSpent(spent: Spent) {
        _spentDetailLiveData.value = spent
    }
}