// GameUtils.kt
package com.example.tictactoe.logic

fun wincheck(board: Array<Array<String>>, symbol: String): Boolean {
    val size = board.size
    for (i in 0 until size) {
        if ((0 until size).all { board[i][it] == symbol }) return true
        if ((0 until size).all { board[it][i] == symbol }) return true
    }
    if ((0 until size).all { board[it][it] == symbol }) return true
    if ((0 until size).all { board[it][size - 1 - it] == symbol }) return true
    return false
}

fun makeEasyAIMove(board: Array<Array<String>>): Pair<Int, Int>? {
    val emptyCells = mutableListOf<Pair<Int, Int>>()
    for (i in board.indices) {
        for (j in board[i].indices) {
            if (board[i][j].isEmpty()) {
                emptyCells.add(Pair(i, j))
            }
        }
    }
    return emptyCells.randomOrNull()
}
