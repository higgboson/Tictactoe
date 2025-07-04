// MainMenuScreen.kt
package com.example.tictactoe.screens

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.tictactoe.R
import com.example.tictactoe.navigation.Screen

@Composable
fun ModemenuScreen(onModeSelected: (String) -> Unit)// on mode selected was not needed if we did not use navigation compose , when we click the  coop button, it gets stored in the parameter called onmodeslecteed , which then returns the  value as coop into the mode ->lambda function in navigation console
{  val tictactoetitle = FontFamily(Font(R.font.tictactoetitle, FontWeight.Normal))
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.neww),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { onModeSelected("coop") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.3f),//SEMITRANSPARENT BUTTON
                    contentColor = Color.Black
                ))
            {

                Text("Co-op Mode", fontWeight= FontWeight.Bold, fontFamily= tictactoetitle,fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onModeSelected("AI") },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.3f),
                    contentColor = Color.Black
                )
                )
            {
                Text("AI Mode", fontWeight= FontWeight.Bold, fontFamily= tictactoetitle,fontSize = 30.sp)
            }
        }
    }
}