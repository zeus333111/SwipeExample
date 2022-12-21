package com.example.swipeexample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swipeexample.R
import com.example.swipeexample.databinding.ChipFragmentBinding
import com.example.swipeexample.databinding.ChipItemBinding
import com.example.swipeexample.presentation.viewmodel.ChipsViewModel
import com.google.android.material.chip.Chip

class ChipsFragment: Fragment() {

    private val viewModel: ChipsViewModel by viewModels()
    private lateinit var binding: ChipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChipFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getChips()
    }

    private fun setUpObservers() {
        viewModel.chipInfo.observe(viewLifecycleOwner) { listData ->
            listData.forEach {
                val chip = createChip(it)
                binding.chipGroup.addView(chip)
            }
        }
    }

    private fun createChip(text: String): Chip {
        val chip = ChipItemBinding.inflate(layoutInflater).root
        chip.text = text
        chip.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                viewModel.addSelection(text)
            } else {
                viewModel.removeSelected(text)
            }
        }
        return chip
    }
}