package com.enigmacamp.mysimpleespresso.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigmacamp.mysimpleespresso.BaseApplication
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.databinding.ActivitySecondBinding
import com.enigmacamp.mysimpleespresso.repository.SpentRepository
import com.enigmacamp.mysimpleespresso.utils.CountingIdlingResourceSingleton

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        subscribe()
        CountingIdlingResourceSingleton.increment()
        viewModel.getRecentSpent()
        binding.apply {
            buttonHome.setOnClickListener {
                finish()
            }
            recyclerViewSpent.layoutManager = LinearLayoutManager(this@SecondActivity)
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = SpentRepository((application as BaseApplication).spentDatabase)
                return SecondActivityViewModel(repository) as T
            }
        }).get(SecondActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.spentListLiveData.observe(this, {
            binding.recyclerViewSpent.adapter = SpentViewAdapter(it) {
                viewModel.getDetailSpent(it)
            }
            CountingIdlingResourceSingleton.decrement()
        })

        viewModel.spentDetailLiveData.observe(this, {
            binding.textViewSpentSelection.text = it.spentDescription
        })
    }
}