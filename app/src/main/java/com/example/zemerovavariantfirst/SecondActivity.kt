package com.example.zemerovavariantfirst

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.zemerovavariantfirst.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var bcSecond : ActivitySecondBinding
    lateinit var shPref : SharedPreferences
    val figures = arrayOf("Треугольник", "Круг")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bcSecond = ActivitySecondBinding.inflate((layoutInflater))
        setContentView(bcSecond.root)

        var value : String
        val spinner = bcSecond.spinnerChoiceF
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, figures)
        shPref = getPreferences(MODE_PRIVATE)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long)
            {
                when(figures[position].toString())
                {
                    "Треугольник" -> {
                        bcSecond.iVFigure.setImageResource(R.drawable.triangle)
                    }
                    "Круг" -> {
                        bcSecond.iVFigure.setImageResource(R.drawable.circle)
                    }
                    else ->{
                        bcSecond.iVFigure.setImageResource(R.drawable.triangle)
                    }
                }
                shPref.edit().putString("selectedfigure", figures[position].toString()).apply()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        bcSecond.buttonResult.setOnClickListener {

            val name = shPref.getString("selectedfigure", "").toString()
            value = bcSecond.eTPreformData.text.toString()

            if(value.isNotEmpty())
            {
                try{
                    value.toInt()
                    if(value.toInt() > 0)
                    {
                        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                        intent.putExtra("name", name)
                        intent.putExtra("value", value.toInt())
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this,"Только положительные целые числа", Toast.LENGTH_SHORT).show()
                    }
                }
                catch(e: NumberFormatException){
                    Toast.makeText(this,"Только положительные целые числа", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this,"Поле ввода не может быть пустым", Toast.LENGTH_SHORT).show()
            }
        }
    }
}