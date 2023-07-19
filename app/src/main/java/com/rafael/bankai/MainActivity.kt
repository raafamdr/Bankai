package com.rafael.bankai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rafael.bankai.ui.BankaiApiStatus
import com.rafael.bankai.ui.OverviewViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        val viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        splashScreen.setKeepOnScreenCondition{
            viewModel.status.value != BankaiApiStatus.DONE
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Hiding tool bar
        supportActionBar?.hide()
    }
}