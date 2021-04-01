package com.ss.pretest.Fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.ss.pretest.BaseFragment
import com.ss.pretest.Data.login_data
import com.ss.pretest.MainActivity2
import com.ss.pretest.R
import kotlinx.coroutines.launch
import org.json.JSONObject


class LoginFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init()
    {
        var submit = view?.findViewById<Button>(R.id.submit)
        var phone = view?.findViewById<EditText>(R.id.phone)
        var pass = view?.findViewById<EditText>(R.id.pin)

        submit!!.setOnClickListener()
        {
           if (phone!!.text.isNotEmpty())
           {
               if (!phone.text.toString().startsWith("0"))
               {
                   if (pass!!.text.isNotEmpty())
                   {
                       login(phone.text.toString(), pass.text.toString())
                   }
                   else
                   {
                       Toast.makeText(context, "Please provide a password", Toast.LENGTH_LONG).show()
                   }
               }
               else
               {
                   Toast.makeText(context, "Phone number can not start with a 0", Toast.LENGTH_LONG).show()
               }
           }
            else
           {
               Toast.makeText(context, "Please provide a phone number", Toast.LENGTH_LONG).show()
           }

        //login()
        }
    }

    fun login(phone: String, pass: String) {
        val json = JSONObject()
        json.put("mobile", phone)
        json.put("password", pass)

        launch {

            "http://52.149.222.217:5001/api/auth/login"
                    .httpPost()
                    .header("Content-Type" to "application/json")
                    .header("Module" to "JW9tc0ByZWRsdGQl")
                    .body(json.toString())
                    .responseObject(login_data.Deserializer())
                    { request, response, result ->

                        when (result) {
                            is com.github.kittinunf.result.Result.Failure -> {

                                val ex = result.getException()
                                Log.e("sajib1", ex.toString())

                            }
                            is com.github.kittinunf.result.Result.Success -> {

                                Log.e("sajib", result.value.payload.token)
                                val pref = PreferenceManager.getDefaultSharedPreferences(context)
                                val editor = pref.edit()
                                editor
                                    .putString("Token", result.value.payload.token)
                                    .apply()


                                val prefsEditor: SharedPreferences.Editor = pref.edit()
                                val gson = Gson()
                                val json = gson.toJson(result.value)
                                prefsEditor.putString("MyObject", json)
                                prefsEditor.commit()


//                                val gson = Gson()
//                                val json: String = pref.getString("MyObject", "null")
//                                val obj: login_data = gson.fromJson(json, login_data::class.java)


                                updateUI {
                                    activity?.finish()
                                    startActivity(Intent(context, MainActivity2::class.java))
                                }

                            }


                        }
                    }

        }


    }
}