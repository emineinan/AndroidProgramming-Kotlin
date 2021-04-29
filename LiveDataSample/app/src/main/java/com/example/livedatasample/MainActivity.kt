package com.example.livedatasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatasample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds().observe(this, Observer {
            binding.textViewCounter.text = it.toString()
        })
        viewModel.finished().observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_LONG).show()
            }
        })

        binding.buttonStart.setOnClickListener {
            if (binding.editTextValue.text.isEmpty() || binding.editTextValue.text.length < 4) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.timerValue().value= binding.editTextValue.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        binding.buttonStop.setOnClickListener {
            binding.textViewCounter.text="0"
            binding.editTextValue.text.clear()
            viewModel.stopTimer()
        }
    }
}