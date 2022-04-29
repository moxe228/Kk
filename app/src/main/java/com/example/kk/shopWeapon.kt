package com.example.kk


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_weapon)

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
}