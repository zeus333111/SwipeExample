package com.example.swipeexample.domain.interactors

import com.example.swipeexample.data.PreferencesStore

class SetFlogUseCase (private val preferences: PreferencesStore) {

    operator fun invoke(flag: Boolean) {
        preferences.setFlag(flag)
    }
}