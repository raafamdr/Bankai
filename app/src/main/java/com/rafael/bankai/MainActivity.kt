package com.rafael.bankai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.rafael.bankai.ui.BankaiApiStatus
import com.rafael.bankai.ui.OverviewViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        val viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        splashScreen.setKeepOnScreenCondition {
            viewModel.status.value != BankaiApiStatus.DONE
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        // Handling the tool bar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar?.hide()
                }
                R.id.infoFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

                }
                R.id.charactersListFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                }
                R.id.quotesFragment -> {
                    supportActionBar?.hide()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}