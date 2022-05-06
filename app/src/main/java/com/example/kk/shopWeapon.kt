package com.example.kk


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private var haveS1 = false
private var haveS2 = false
private var haveS3 = false

public var equipS1 = false
public var equipS2 = false
public var equipS3 = false

class shopWeapon : AppCompatActivity() {
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_weapon)

        pref = getSharedPreferences("TABLESW", Context.MODE_PRIVATE)
        delAll()
        haveS1 = pref?.getBoolean("hS1", false)!!
        haveS2 = pref?.getBoolean("hS2", false)!!
        haveS3 = pref?.getBoolean("hS3", false)!!
        equipS1 = pref?.getBoolean("eS1", false)!!
        equipS2 = pref?.getBoolean("eS2", false)!!
        equipS3 = pref?.getBoolean("eS3", false)!!

        val buttonS1: Button = findViewById(R.id.buttonS1)
        val buttonS2: Button = findViewById(R.id.buttonS2)
        val buttonS3: Button = findViewById(R.id.buttonS3)

        if (haveS1) buttonS1.text = "equip"
        if (equipS1) buttonS1.isEnabled = !equipS1
        if (haveS2) buttonS2.text = "equip"
        if (equipS2) buttonS2.isEnabled = !equipS2
        if (haveS3) buttonS3.text = "equip"
        if (equipS3) buttonS3.isEnabled = !equipS3

        buttonS1.setOnClickListener {
            if (haveS1) {
                equip(true, false, false, buttonS1, buttonS2, buttonS3)
                damage = 2
            } else {
                if (wallet >= 10) {
                    wallet -= 10
                    damage = 2
                    haveS1 = true
                    buttonS1.text = "equip"
                    equip(true, false, false, buttonS1, buttonS2, buttonS3)
                }
            }
        }

        buttonS2.setOnClickListener {
            if (haveS2) {
                equip(false, true, false, buttonS1, buttonS2, buttonS3)
                damage = 3
            } else {
                if (wallet >= 20) {
                    wallet -= 20
                    damage = 3
                    haveS2 = true
                    buttonS2.text = "equip"
                    equip(false, true, false, buttonS1, buttonS2, buttonS3)
                }
            }
        }

        buttonS3.setOnClickListener {
            if (haveS3) {
                equip(false, false, true, buttonS1, buttonS2, buttonS3)
                damage = 4
            } else {
                if (wallet >= 30) {
                    wallet -= 30
                    damage = 4
                    haveS3 = true
                    buttonS3.text = "equip"
                    equip(false, false, true, buttonS1, buttonS2, buttonS3)
                }
            }
        }
    }

    fun saveDate(hS1: Boolean, hS2: Boolean, hS3: Boolean, eS1: Boolean, eS2: Boolean, eS3: Boolean) {
        val editor = pref?.edit()
        editor?.putBoolean("hS1", hS1)
        editor?.putBoolean("hS2", hS2)
        editor?.putBoolean("hS3", hS3)
        editor?.putBoolean("eS1", eS1)
        editor?.putBoolean("eS2", eS2)
        editor?.putBoolean("eS3", eS3)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveDate(haveS1, haveS2, haveS3, equipS1, equipS2, equipS3)
    }

    fun delAll(){
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
    }
}