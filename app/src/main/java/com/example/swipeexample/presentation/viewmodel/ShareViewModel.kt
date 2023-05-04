package com.example.swipeexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipeexample.domain.interactors.GetFlagUseCase
import com.example.swipeexample.domain.interactors.SetFlogUseCase
import kotlinx.coroutines.launch

class ShareViewModel(
    private val getFlagUseCase: GetFlagUseCase,
    private val setFlogUseCase: SetFlogUseCase
): ViewModel() {

    private val _flag = MutableLiveData<Boolean>()
    val flag: LiveData<Boolean> = _flag

    fun setFlag(flag: Boolean) {
        setFlogUseCase.invoke(flag)
    }

    fun getFlag() = viewModelScope.launch {
        getFlagUseCase.invoke().collect {
            _flag.postValue(it)
        }
    }
}