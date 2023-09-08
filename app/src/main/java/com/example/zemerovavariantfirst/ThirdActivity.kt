package com.example.zemerovavariantfirst

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.zemerovavariantfirst.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var bcThird: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bcThird = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(bcThird.root)

        var name = intent.getStringExtra("name").toString()
        var result = intent.getIntExtra("value", 0).toString().toInt()
        var p : Int
        var r : Double

        when(name)
        {
            "Треугольник" -> {
                p = result * 3
                bcThird.tVResult.text = "$p"
                bcThird.iVFigure.setImageResource(R.drawable.triangle)
            }
            "Круг" -> {
                r = result / 2 * 3.14
                bcThird.tVResult.text = String.format("%.2f", r)
                bcThird.iVFigure.setImageResource(R.drawable.circle)
            }
            else ->{
                name = "Не определено"
                bcThird.tVResult.text = "0"
            }
        }

        bcThird.eTNameF.text = "$name"

        bcThird.buttonSignUp.setOnClickListener {
            val intent = Intent(this@ThirdActivity, FirstActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}