
package com.example.tictactoe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R

@Composable
fun SecondGameScreen(onSizeSelected: (Int) -> Unit, onBack: () -> Unit) {
    var sizeofboard by remember { mutableStateOf<Int?>(null) }
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.board),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (sizeofboard == null) {
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Spacer(modifier = Modifier.height(24.dp))
                val tictactoetitle = FontFamily(Font(R.font.tictactoetitle, FontWeight.Normal))

                listOf(3, 4, 5, 6, 7, 8, 9, 10).forEach { size ->
                    Button(
                        onClick = { sizeofboard = size },


                                    colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White.copy(alpha = 0.3f),
                            contentColor = Color.Black
                        )
                    )

                    {
                        Text("$size x $size", fontWeight= FontWeight.Bold, fontFamily= tictactoetitle,fontSize = 30.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onBack)
                {
                    Text("Back")
                }
            }
        } else {
            onSizeSelected(sizeofboard!!)
        }
    }}