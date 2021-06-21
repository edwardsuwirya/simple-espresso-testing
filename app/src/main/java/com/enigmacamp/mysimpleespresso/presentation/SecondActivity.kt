package com.enigmacamp.mysimpleespresso.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enigmacamp.mysimpleespresso.R
import com.enigmacamp.mysimpleespresso.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            buttonHome.setOnClickListener {
                finish()
            }
        }
    }
}