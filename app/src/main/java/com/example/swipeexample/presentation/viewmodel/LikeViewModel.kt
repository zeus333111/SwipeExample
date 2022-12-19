package com.example.swipeexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LikeViewModel: ViewModel() {

    private val data = listOf("DynamicCard1", "DynamicCard2", "DynamicCard3", "DynamicCard4", "DynamicCard5", "DynamicCard6", "DynamicCard7")

    private val _firstCard = MutableLiveData<String>()
    val firstCard: LiveData<String> = _firstCard

    private val _secondCard = MutableLiveData<String>()
    val secondCard: LiveData<String> = _secondCard

    private var position = 0

    fun getCards() {
        _firstCard.postValue(data[position])
        _secondCard.postValue(data[position + 1])
    }

    fun nextCard() {
        position++
        _firstCard.postValue(data[position])
        _secondCard.postValue(data[position + 1])
    }
}