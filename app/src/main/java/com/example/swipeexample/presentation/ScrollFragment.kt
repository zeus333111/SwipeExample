package com.example.swipeexample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swipeexample.databinding.ScrollFragmentBinding

class ScrollFragment: Fragment() {

    private lateinit var binding: ScrollFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScrollFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}