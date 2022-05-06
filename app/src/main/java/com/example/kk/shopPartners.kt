package com.example.kk


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


private var p1 = false
private var p2 = false
private var p3 = false

class shopPartners : AppCompatActivity() {
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_partners)

        pref = getSharedPreferences("TABLESP", Context.MODE_PRIVATE)
        delAll()
        p1 = pref?.getBoolean("p1", false)!!
        p2 = pref?.getBoolean("p2", false)!!
        p3 = pref?.getBoolean("p3", false)!!

        val buttonP1: Button = findViewById(R.id.buttonP1)
        val buttonP2: Button = findViewById(R.id.buttonP2)
        val buttonP3: Button = findViewById(R.id.buttonP3)

        if (p1) buttonP1.isEnabled = false
        if (p2) buttonP2.isEnabled = false
        if (p3) buttonP3.isEnabled = false

        buttonP1.setOnClickListener {
            if (wallet >= 10) {
                passiveDamage += 1
                wallet -= 10
                p1 = true
                buttonP1.isEnabled = false
            }
        }

        buttonP2.setOnClickListener {
            if (wallet >= 20) {
                passiveDamage += 2
                wallet -= 20
                p2 = true
                buttonP2.isEnabled = false
            }
        }

        buttonP3.setOnClickListener {
            if (wallet >= 30) {
                passiveDamage += 3
                wallet -= 30
                p3 = true
                buttonP3.isEnabled = false
            }
        }
    }

    private fun saveData(p1: Boolean, p2: Boolean, p3: Boolean){
        val editor = pref?.edit()
        editor?.putBoolean("p1", p1)
        editor?.putBoolean("p2", p2)
        editor?.putBoolean("p3", p3)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData(p1, p2, p3)
    }

    fun delAll(){
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
    }
}