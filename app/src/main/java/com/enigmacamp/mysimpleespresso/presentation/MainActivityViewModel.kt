package com.enigmacamp.mysimpleespresso.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(private val spentRepository: SpentRepository) : ViewModel() {

    private var _messageNotificationLiveData = MutableLiveData<String>()
    val meessageNotificationLiveData: LiveData<String>
        get() = _messageNotificationLiveData

    fun addNewSpent(spent: Spent) {
        viewModelScope.launch {
            spentRepository.addSpent(spent)
            _messageNotificationLiveData.postValue("Successfully add your spent")
        }
    }
}