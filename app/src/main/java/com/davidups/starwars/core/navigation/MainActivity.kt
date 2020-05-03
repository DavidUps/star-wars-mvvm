package com.davidups.starwars.core.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.davidups.skell.R
import kotlinx.android.synthetic.main.navigation_activity.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(bottom_nav, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = when (destination.id) {
                R.id.movies -> destination.label
                R.id.people -> destination.label
                else -> ""
            }

            bottom_nav.visibility = when (destination.id) {
                R.id.authentication -> View.GONE
                else -> View.VISIBLE
            }
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    public fun toolbarText(text: String) {
        toolbar.title = text
    }
}
