package com.enigmacamp.mysimpleespresso.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.enigmacamp.mysimpleespresso.BaseApplication
import com.enigmacamp.mysimpleespresso.databinding.ActivityMainBinding
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import com.enigmacamp.mysimpleespresso.utils.CountingIdlingResourceSingleton
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
                CountingIdlingResourceSingleton.increment()
                val spentAmount = editTextSpentAmount.text.toString()
                val spentDesc = editTextSpentDescription.text.toString()
                viewModel.addNewSpent(spentAmount, spentDesc)
            }

            buttonViewSpent.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
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
                textViewMessage.text = it
                editTextSpentAmount.text.clear()
                editTextSpentDescription.text.clear()
                viewModel.getRecentSpent()
            }
        })

        viewModel.spentListLiveData.observe(this, {
            val job = lifecycleScope.launch {
                delay(1500)
                binding.textViewMessage.setText(it)
                delay(1500)
                binding.textViewMessage.text = ""
            }
            job.invokeOnCompletion {
                CountingIdlingResourceSingleton.decrement()
            }
        })
    }
}