package com.example.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.textViewCounter.text = viewModel.number.toString()

        binding.buttonAdd.setOnClickListener {
            viewModel.addNumber()
            binding.textViewCounter.text = viewModel.number.toString()
        }
    }
}