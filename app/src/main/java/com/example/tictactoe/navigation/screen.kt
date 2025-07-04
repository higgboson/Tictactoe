package com.example.tictactoe.navigation


sealed class Screen(val route: String)// sealed class is best to avoid typo errors
{
    object Splash : Screen("intro_page")
    object Name : Screen("username")
    object Modemenu : Screen("main_menu")
    object BoardSelector : Screen("board_selector/{mode}")
    {
        fun createRoute(mode: String) = "board_selector/$mode" //it means we reach our destination via coop or ai mode, unlike before we have branches here and we have to go to next page through one of the branch
    }
    object Game : Screen("game/{mode}/{size}")
    {
        fun createRoute(mode: String, size: Int) = "game/$mode/$size"
    }
}
