package com.enigmacamp.mysimpleespresso.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import kotlinx.coroutines.launch
import java.util.*

class MainActivityViewModel(private val spentRepository: SpentRepository) : ViewModel() {

    private var _messageNotificationLiveData = MutableLiveData<String>()
    val meessageNotificationLiveData: LiveData<String>
        get() = _messageNotificationLiveData

    fun addNewSpent(spentAmount: String, spentDescription: String) {
        viewModelScope.launch {
            if (spentAmount.isNullOrBlank() || spentDescription.isNullOrBlank()) {
                _messageNotificationLiveData.postValue("Error, please fill your data completely")
            } else {
                val newSpent = Spent(
                    spentAmount = spentAmount.toDouble(),
                    spentDate = Date(),
                    spentDescription = spentDescription,
                )
                spentRepository.addSpent(newSpent)
                _messageNotificationLiveData.postValue("Successfully add your spent")
            }

        }
    }

    fun getRecentSpent() {
        viewModelScope.launch {
            val spents = spentRepository.getFirst5()
            Log.d("MainActivity", "getRecentSpent: ${spents.size}")
            spents.forEach { spent ->
                Log.d("MainActivity", "$spent")
            }
        }
    }
}