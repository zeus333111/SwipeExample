package com.example.swipeexample.domain.interactors

import com.example.swipeexample.data.PreferencesStore
import kotlinx.coroutines.flow.Flow

class GetFlagUseCase( val preferences: PreferencesStore) {

    operator fun invoke(): Flow<Boolean> {
        return preferences.getFlag()
    }
}