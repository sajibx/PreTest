package com.ss.pretest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpPost
import com.google.android.material.snackbar.Snackbar
import com.ss.pretest.Data.login_data
import com.ss.pretest.Fragments.LoginFragment
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        init()
    }


    fun init()
    {

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val token = getString("Token", "Null")

            if (token == "Null")
            {
                val transaction = supportFragmentManager!!.beginTransaction()
                var fragment = LoginFragment()
                transaction.replace(R.id.univarsal, fragment)
                //transaction.addToBackStack(null)
                transaction.commit()
            }
            else
            {
                finish()
                startActivity(Intent(this@MainActivity, MainActivity2::class.java))
            }
        }

    }

}