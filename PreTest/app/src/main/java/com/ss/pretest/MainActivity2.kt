package com.ss.pretest

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Take appropriate action for each action item click
        return when (item.getItemId()) {
            R.id.action_setting -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()
                val pref = PreferenceManager.getDefaultSharedPreferences(this)
                val editor = pref.edit()
                editor
                    .putString("Token", "Null")
                    .putString("MyObject", "Null")
                    .apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
                true
            }
            else -> {
                false
            }
        }
    }

}