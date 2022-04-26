package com.example.kk


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class shopPartners : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_partners)

        val buttonP1: Button = findViewById(R.id.buttonP1)
        val buttonP2: Button = findViewById(R.id.buttonP2)
        val buttonP3: Button = findViewById(R.id.buttonP3)

        buttonP1.setOnClickListener{
            if(wallet >= 10){
                passiveDamage += 1
                wallet -= 10
                buttonP1.setEnabled(false)
            }
        }

        buttonP2.setOnClickListener{

        }

        buttonP3.setOnClickListener{

        }
    }
}