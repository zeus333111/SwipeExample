package com.example.swipeexample.utils

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

internal fun TextInputLayout.validateEmpty() {
    editText?.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            error = if (editText?.text.toString().isNotEmpty()) {
                null
            } else {
                "No dejes vacios"
            }
        }
    }
}

var emailValid = false
var passwordValid = false

internal fun View.checkForEnabled(
    tietEmail: TextInputEditText,
    tietPassword: TextInputEditText
) {

    tietEmail.doAfterTextChanged { editable ->
        editable?.toString()?.let { email ->
            emailValid = email.isNotEmpty()
            isEnabled = emailValid && passwordValid
        }
    }

    tietPassword.doAfterTextChanged { editable ->
        editable.toString().let { password ->
            passwordValid = password.isNotEmpty()
            isEnabled = emailValid && passwordValid
        }
    }
}