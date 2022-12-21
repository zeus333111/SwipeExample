package com.example.swipeexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChipsViewModel: ViewModel() {

    private val dummyData = listOf("Chip1","Chip2","Chip3","Chip4","Chip5","Chip6","Chip7","Chip8","Chip9")
    private val selectionList = mutableListOf<String>()

    private val _chipInfo = MutableLiveData<List<String>>()
    val chipInfo: LiveData<List<String>> = _chipInfo

    fun getChips() {
        _chipInfo.postValue(dummyData)
    }

    fun addSelection(item: String) {
        selectionList.add(item)
    }

    fun removeSelected(item: String) {
        selectionList.remove(item)
    }
    
    fun submitData() {
        //submitDataUseCase.invoke(selectedList)
    }
}