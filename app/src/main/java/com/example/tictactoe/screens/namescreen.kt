

package com.example.tictactoe.screens

import android.R.id.input
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R

@Composable
fun nameScreen(inputname: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.newname),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        val tictactoetitle = FontFamily(Font(R.font.tictactoetitle))

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = tictactoetitle
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { if (name.isNotBlank()) inputname(name) },
                enabled = name.isNotBlank()
            )
            {
                Text("Continue")
            }
        }
    }
}
