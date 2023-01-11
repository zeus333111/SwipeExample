package com.example.swipeexample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swipeexample.databinding.FormFragmentBinding
import com.example.swipeexample.utils.checkForEnabled
import com.example.swipeexample.utils.validateEmpty

class FormFragment : Fragment() {

    private lateinit var binding: FormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupValidator()

        binding.ivButton.setOnClickListener {
            Toast.makeText(requireContext(), "clickeado", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupValidator() = with(binding) {
        tilEmail.validateEmpty()
        tilPassword.validateEmpty()

        ivButton.checkForEnabled(
            tietEmail,
            tietPassword
        )
    }
}