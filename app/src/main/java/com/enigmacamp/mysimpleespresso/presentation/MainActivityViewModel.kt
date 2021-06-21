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

    private var _spentListLiveData = MutableLiveData<String>()
    val spentListLiveData: LiveData<String>
        get() = _spentListLiveData

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
            val spentList = mutableListOf<String>()
            spents.forEach { spent ->
                spentList.add(spent.spentDescription)
            }
            val spentListString = spentList.joinToString(",")
            Log.d("MainActivity", spentListString)
            _spentListLiveData.postValue(spentListString)
        }
    }
}