package com.example.livedatasample

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private lateinit var timer: CountDownTimer
    private val seconds = MutableLiveData<Int>()
    private var finished = MutableLiveData<Boolean>()
    private var timerValue= MutableLiveData<Long>()

    fun seconds(): LiveData<Int> {
        return seconds
    }

    fun finished(): LiveData<Boolean> {
        return finished
    }

    fun timerValue(): MutableLiveData<Long> {
        return timerValue
    }

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onFinish() {
                finished.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                seconds.value = timeLeft.toInt()
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}