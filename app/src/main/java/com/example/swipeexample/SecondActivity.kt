package com.example.swipeexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.swipeexample.databinding.ShareFragmentBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ShareFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShareFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = intent.getStringExtra("data")
        val title = intent.getStringExtra("body")
        val otro = intent.extras?.getString("body")
        Log.e("bundle", "data:$data\ntitle:$title\notro:$otro")
        //todo navigation


    }
}
