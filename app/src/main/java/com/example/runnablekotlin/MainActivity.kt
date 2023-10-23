package com.example.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var runnable : Runnable
    private var handler : Handler = Handler(Looper.getMainLooper())
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonOnClick(view : View){
        if(view.id == R.id.startButton){
            number = 0

            runnable = object : Runnable{
                override fun run() {
                    number++
                    binding.textView.text = "Time : ${number}"

                    handler.postDelayed(runnable,1000)
                    //this -> runnable , this@MainActivity - > ActivityMain
                    //handler.postDelayed(this,1000)
                }

            }
            handler.post(runnable)
            binding.startButton.isEnabled = false
        }
        if(view.id == R.id.stopButton){
            binding.startButton.isEnabled = true
            number = 0
            binding.textView.text = "Time : ${number}"
            handler.removeCallbacks(runnable)
        }
    }
}