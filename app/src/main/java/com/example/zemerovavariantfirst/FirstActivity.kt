package com.example.zemerovavariantfirst

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.zemerovavariantfirst.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    lateinit var bcFirst : ActivityFirstBinding
    lateinit var shPref : SharedPreferences
    lateinit var login : EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bcFirst = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(bcFirst.root)

        var count = "0"
        shPref = getPreferences(MODE_PRIVATE)

        if (shPref.getString("count", "").toString().isNotEmpty())
        {
            count = shPref.getString("count", "").toString()
        }
        login = bcFirst.eTLogin
        password = bcFirst.eTPassword


        login.setText(shPref.getString("login", ""))
        password.setText(shPref.getString("password", ""))

        bcFirst.buttonSignUp.setOnClickListener {

            if(login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
            {
                Saving()

                if(count == "1")
                {
                    if(login.text.toString() == "ects" && password.text.toString() == "ects2023")
                    {
                        count = "0"
                        shPref = getPreferences(MODE_PRIVATE)
                        val ed = shPref.edit()
                        ed.putString("count", count.toString())
                        ed.apply()

                        FierstToSecond()
                    }
                    else
                    {
                        Toast.makeText(this,"логин = ects, пароль = ects2023",Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    count = "1"
                    shPref = getPreferences(MODE_PRIVATE)
                    val ed = shPref.edit()
                    ed.putString("count", count.toString())
                    ed.apply()

                    FierstToSecond()
                }

            }
            else
            {
                Toast.makeText(this,"Введите логин и пароль",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun FierstToSecond()
    {
        val intent = Intent(this@FirstActivity, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun Saving()
    {
        shPref = getPreferences(MODE_PRIVATE)

        val ed = shPref.edit()
        ed.putString("login", login.text.toString())
        ed.putString("password", password.text.toString())
        ed.apply()
    }

    fun Autofill(v : View)
    {

        if(login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
        {

            if(v.id == R.id.buttonSignUp)
            {
                Saving()
            }

        }
        else
        {
            shPref = getPreferences(MODE_PRIVATE)
            login.setText(shPref.getString("login", ""))
            password.setText(shPref.getString("password", ""))
        }

    }
}