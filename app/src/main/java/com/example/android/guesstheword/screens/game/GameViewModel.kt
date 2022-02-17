package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "app_GameViewModel"

class GameViewModel : ViewModel() {

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i(TAG, "GameViewModel created")
        resetList()
        nextWord()
        _score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "GameViewModel destroyed")
    }

    private fun resetList() {
        wordList = mutableListOf(
            "Королева",
            "Больница",
            "Баскетбол",
            "Кот",
            "Сдача",
            "Улитка",
            "Суп",
            "Календарь",
            "Грусть",
            "Стол",
            "Гитара",
            "Дом",
            "Рельсы",
            "Зебра",
            "Желе",
            "Машина",
            "Толпа",
            "Торговля",
            "Сумка",
            "Рулет",
            "Пузырь"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
}