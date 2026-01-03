package com.app.thinkerlab.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.thinkerlab.ui.components.EBikeMileageRouter
import com.app.thinkerlab.ui.components.EBikePage

class EBikeOverviewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EBikeOverViewApp()
        }
    }

    @Composable
    fun EBikeOverViewApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "EBikeRangeScreen") {
            composable("EBikeRangeScreen") { EBikePage(navController) }
            composable("EBikeMileageScreen") { EBikeMileageRouter(navController) }
        }
    }
}