package com.enigmacamp.mysimpleespresso.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.enigmacamp.mysimpleespresso.BaseApplication
import com.enigmacamp.mysimpleespresso.data.Spent
import com.enigmacamp.mysimpleespresso.databinding.ActivityMainBinding
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            buttonAddSpent.setOnClickListener {
                val spentAmount = editTextSpentAmount.text.toString()
                val spentDesc = editTextSpentDescription.text.toString()
                viewModel.addNewSpent(spentAmount, spentDesc)
            }
        }
        initViewModel()
        subscribe()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = SpentRepository((application as BaseApplication).spentDatabase)
                return MainActivityViewModel(repository) as T
            }
        }).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.meessageNotificationLiveData.observe(this, {
            binding.apply {
                textViewMessage.setText(it)
                editTextSpentAmount.text.clear()
                editTextSpentDescription.text.clear()
                lifecycleScope.launch {
                    delay(1500)
                    textViewMessage.text = ""
                    viewModel.getRecentSpent()
                }
            }

        })
    }
}