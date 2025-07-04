package com.example.tictactoe.screens

import android.R.attr.maxWidth
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.logic.makeEasyAIMove
import com.example.tictactoe.logic.wincheck

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TicTacToeGame1(size: Int, playerName: String,onBack: () -> Unit) {
    val board = remember {
        mutableStateListOf<SnapshotStateList<String>>().apply {
            repeat(size) {
                add(mutableStateListOf(*Array(size) { "" }))
            }
        }
    }

    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    Box(modifier= Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.neeeew),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val screenWidth = maxWidth
        val boxSize = (screenWidth - 16.dp * 2) / size
        val tictactoetitle = FontFamily(Font(R.font.tictactoetitle, FontWeight.Normal))

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Tic Tac Toe ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontFamily = tictactoetitle,
                color = Color.Yellow
            )
            Spacer(modifier = Modifier.height(16.dp))


            for (i in 0 until size) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (j in 0 until size) {
                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .padding(2.dp)
                                .background(brush = linearGradient(colors = listOf(Color(0xFFFBAB7E), Color(0xFFF7CE68))), RoundedCornerShape(8.dp))
                                .clickable {
                                    if (board[i][j].isEmpty() && winner == null && currentPlayer == "X")
                                    {
                                        board[i][j] = currentPlayer
                                        val convertedBoard =
                                            board.map { it.toTypedArray() }.toTypedArray()

                                        if (wincheck(convertedBoard, currentPlayer)) {
                                            winner = currentPlayer
                                        }
                                        else if (board.all { row -> row.all { it.isNotEmpty() } })
                                        {
                                            winner = "Draw"
                                        }
                                        else
                                        {
                                            currentPlayer = "O"

                                            val aiMove = makeEasyAIMove(convertedBoard)
                                            aiMove?.let { (aiRow, aiCol) ->
                                                board[aiRow][aiCol] = "O"

                                                val updatedBoard =
                                                    board.map { it.toTypedArray() }.toTypedArray()

                                                if (wincheck(updatedBoard, "O"))
                                                {
                                                    winner = "O"
                                                }
                                                else if (board.all { row -> row.all { it.isNotEmpty() } })
                                                {
                                                    winner = "Draw"
                                                }
                                                else
                                                {
                                                    currentPlayer = "X"
                                                }
                                            }
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text(board[i][j], fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            winner?.let {
                if (it == "Draw") {
                   winner = "Game Drawn!"
                }
                else if (it == "X")
                {
                   winner = "$playerName Wins!"
                }
                else if (it == "O")
                {
                   winner = "AI wins"
                }
                else
                {
                    ""
                }
                Text(
                    text = winner!!,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (it == "Draw") Color.Yellow else Color.Green
                )
                Button(
                    onClick = {
                        for (i in board.indices) {
                            for (j in board[i].indices) {
                                board[i][j] = ""
                            }
                        }
                        winner = null
                        currentPlayer = "X"
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.3f),
                        contentColor = Color.Black)
                ) {
                    Text("Play Again", fontWeight= FontWeight.Bold, fontFamily= tictactoetitle,fontSize = 30.sp, color = Color.Yellow)
                }

            }
        }
    }
}