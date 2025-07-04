

package com.example.tictactoe

import android.R.attr.name
import com.example.tictactoe.screens.SecondGameScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.tictactoe.navigation.Screen
import com.example.tictactoe.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() //This line is neceassry in every  navigation systems, this acts as a medium to move between screens

            var playerName by rememberSaveable { mutableStateOf("") }// This lne is only needed if you want to remember something across screens and to ensure that the  variable remains intact even after rotation of the screen
             // in the above line , mutable state of is empty because we do not have a name when we open the app

            NavHost(navController = navController, startDestination = Screen.Splash.route) //100%necessary line which defines the starting screen, all composables come under nav host(navhost is like a map for our app)
            {

                composable(Screen.Splash.route) //THIS line asks to provide the route string which is inside the sealed class screen inside splash object
                {
                    splashScreen(navController) //splashscreen function is defined in splashscreen.kt , before we move to the nextscreen there is a delay of 6 seconds, so we use navcontroller.navigate inside the splash screen function in splashscreen.kt after we put the 6seconds delay
                }                              //navcontroller.navigate heps to move to the next screen


                composable(Screen.Name.route)
                {
                    nameScreen{ name ->                      // the name screenfunctionin namescreen.kt receives  name, name-> is a lambda function, it stores the name of the player  into variable player name
                        playerName = name
                        navController.navigate(Screen.Modemenu.route)// after storing name we use navcontroller.navigate to move to next screen which is modemenu
                    }
                }
                composable(Screen.Modemenu.route)
                {
                    ModemenuScreen{ mode ->                 //there is nothing to store here, we first select the button, then we proceed to the next screen
                        navController.navigate(Screen.BoardSelector.createRoute(mode))   // mode -> means that when we click mode, mode menu function inside mode selection.kt is called, when we click coop the onmodeslected(coop) activates
                    }                                                                    //once mode is known ,eg. coop then createroute(coop) function is called which is inside screen sealed class , here via createroute(coop) , the screen goes from main mode to board selected
                }
                composable(
                    route = Screen.BoardSelector.route,
                    arguments = listOf(navArgument("mode")
                    { defaultValue = "coop" })
                )
                { backStackEntry ->
                    val mode = backStackEntry.arguments?.getString("mode") ?: "coop"
                    SecondGameScreen(
                        onSizeSelected = { size ->                                           // second game screen function takes in size of the board and also the back button
                            navController.navigate(Screen.Game.createRoute(mode, size)) // we go to next screen  via mode ( at this point it is already known), then via size of board, then to the target which is game screen
                        },
                        onBack = { navController.popBackStack() } // this back button enables to go back to previous screen
                    )
                }
                composable(
                    route = Screen.Game.route,  //this also follows same as before
                    arguments = listOf(
                        navArgument("mode") {},
                        navArgument("size") { type = NavType.IntType }
                    )
                )
                { backStackEntry ->
                    val mode = backStackEntry.arguments?.getString("mode") ?: "coop"
                    val size = backStackEntry.arguments?.getInt("size") ?: 3

                    if (mode == "coop")
                    {
                        TicTacToeGame(size, playerName) { navController.popBackStack() }
                    }
                    else
                    {
                        TicTacToeGame1(size, playerName) { navController.popBackStack() }
                    }
                }
            }
        }
    }
}









