package com.example.tic_tac_toe_multiplayer.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class OfflineViewModel: ViewModel() {
    private val _buttonStates = mutableStateOf( List(3){ MutableList(3) {true} })
    private val _matrix = mutableStateOf(List(3) {MutableList(3) {'#'} })
    private val _gameState = mutableStateOf('0')
    private val _lastWinner = mutableStateOf("First Match!")
    private val _scoreX = mutableStateOf(0)
    private val _score0 = mutableStateOf(0)

    val buttonStates = _buttonStates
    val matrix = _matrix
    val gameState = _gameState
    val lastWinner = _lastWinner
    val scoreX = _scoreX
    val score0 = _score0

    fun matrixUpdate(row:Int,col:Int){
        _matrix.value[row][col] = _gameState.value
    }
    fun gameStateUpdate(){
         _gameState.value = if(_gameState.value == '0') 'X' else '0'
    }
    fun buttonStateUpdate(row:Int,col:Int){
        _buttonStates.value[row][col] =  false
    }
    fun resetButtons(){
        _buttonStates.value =  List(3) { MutableList(3) { true } }
        _matrix.value = List(3) { MutableList(3) { '#' } }
    }
    fun wins0(){
        _lastWinner.value = "Zero"
        _score0.value++
    }
    fun winsX(){
        _lastWinner.value = "Cross"
        _scoreX.value++
    }
    fun has0Won():Boolean{
        //Row
        for(i in 0..2) {
            var check = true
            for (j in 0..2) {
                if (_matrix.value[i][j] == 'X' || _matrix.value[i][j] == '#') {
                    check = false
                }
            }
            if (check) return true
        }
        //Column
        for(i in 0..2) {
            var check = true
            for (j in 0..2) {
                if (_matrix.value[j][i] == 'X' || _matrix.value[j][i] == '#') {
                    check = false
                }
            }
            if (check) return true
        }
        //forward slash diagonal
        var check = true
        for(i in 0..2){
            if (_matrix.value[i][i] == 'X' || _matrix.value[i][i] == '#') {
                check = false
            }
        }
        if (check) return true
        //backward slash diagonal
        check = true
        if (_matrix.value[0][2] == 'X' || _matrix.value[1][1] == '#') {
            check = false
        }
        if (_matrix.value[1][1] == 'X' || _matrix.value[1][1] == '#') {
            check = false
        }
        if (_matrix.value[2][0] == 'X' || _matrix.value[2][0] == '#') {
            check = false
        }
        return check
    }

    fun hasXWon(): Boolean {
        // Rows
        for (i in 0..2) {
            var check = true
            for (j in 0..2) {
                if (_matrix.value[i][j] != 'X') {
                    check = false
                    break
                }
            }
            if (check) return true
        }

        // Columns
        for (i in 0..2) {
            var check = true
            for (j in 0..2) {
                if (_matrix.value[j][i] != 'X') {
                    check = false
                    break
                }
            }
            if (check) return true
        }

        // Forward slash diagonal
        var check = true
        for (i in 0..2) {
            if (_matrix.value[i][i] != 'X') {
                check = false
                break
            }
        }
        if (check) return true

        // Backward slash diagonal
        check = true
        if (_matrix.value[0][2] != 'X' || _matrix.value[1][1] != 'X' || _matrix.value[2][0] != 'X') {
            check = false
        }
        return check
    }
}