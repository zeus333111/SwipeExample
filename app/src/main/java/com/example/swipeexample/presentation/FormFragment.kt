package com.example.swipeexample.presentation

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swipeexample.databinding.FormFragmentBinding
import com.example.swipeexample.utils.checkForEnabled
import com.example.swipeexample.utils.validateEmpty
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

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

        binding.tietPhone.addTextChangedListener(object : TextWatcher {
            var prevLength = 0
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                prevLength = s?.length ?: 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.tietPhone.removeTextChangedListener(this)
                val formattedPhone: String = when (s.toString().length) {
                    2 -> {
                        s.toString().replaceFirst("^(\\d{2})".toRegex(), "$1 ")
                    }
                    7 -> {
                        s.toString().replaceFirst("(\\d{4})".toRegex(), "$1 ")
                    }
                    else -> {
                        s.toString().replaceFirst("^(\\d{2})(\\d{4})(\\d+)$".toRegex(), "$1 $2 $3")
                    }
                }
                if (prevLength < (s?.length ?: 0)) {
                    binding.tietPhone.setText(formattedPhone)
                    binding.tietPhone.setSelection(formattedPhone.length)
                }
                binding.tietPhone.addTextChangedListener(this)
            }
        })
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