// EnterNameScreen.kt
package com.example.tictactoe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tictactoe.R
import com.example.tictactoe.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController: NavController) {
    LaunchedEffect(Unit)
    {
        delay(6000) // a 6second delay
        navController.navigate(Screen.Name.route)
        {
            popUpTo(Screen.Splash.route)
            { inclusive = true }
        }
    }

    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.newen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        val tictactoetitle = FontFamily(Font(R.font.tictactoetitle, FontWeight.Normal))

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 64.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            CircularProgressIndicator(color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Tic-ing the Tacs", color = Color.Black, fontSize = 20.sp, fontFamily = tictactoetitle)
        }
    }
}
