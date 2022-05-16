package com.example.kk

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

public var boss: Int = 0
public var totalHp: Int = 1
public var damage: Int = 1
public var passiveDamage: Int = 0
public var wallet: Int = 0
public var lvl: Int = 0


public class MainActivity : AppCompatActivity() {
    var preff: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preff = getSharedPreferences("TABLEMA", Context.MODE_PRIVATE)
        if (endGame){
            delAll()
        }
        boss = preff?.getInt("boss", 0)!!
        totalHp = preff?.getInt("totalHp", 0)!!
        damage = preff?.getInt("damage", 1)!!
        passiveDamage = preff?.getInt("passiveDamage", 0)!!
        wallet = preff?.getInt("wallet", 0)!!
        lvl = preff?.getInt("lvl", 0)!!
        lastTexture = preff?.getInt("lt", lastTexture)!!
        maxHp = preff?.getInt("maxHp", maxHp)!!

        val weapon: Button = findViewById(R.id.weapon)
        val partners: Button = findViewById(R.id.partners)

        val money: TextView = findViewById(R.id.money)
        val enemy: ImageButton = findViewById(R.id.enemy)
        val Hp: ProgressBar = findViewById(R.id.progressBarHp)
        val stringHp: TextView = findViewById(R.id.stringHp)


        hpAndReward()
        money.text = "$wallet"
        Hp.max = maxHp
        enemy.setImageResource(lastTexture)
        passiveDamage(enemy, Hp, money, stringHp)

        enemy.setOnClickListener {
            if (lvl > 5) {
                endGame = true
                this.finishAffinity()
            }
            endGame = false
            hpAndReward()
            stringHp.text = "$totalHp"
            Hp.progress = totalHp
            onClick(damage, enemy, Hp, money, stringHp)
        }

        weapon.setOnClickListener {
            val intent = Intent(this@MainActivity, shopWeapon::class.java)
            startActivity(intent)
        }

        partners.setOnClickListener {
            val intent = Intent(this@MainActivity, shopPartners::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {
        val editor = preff?.edit()
        editor?.putInt("boss", boss)
        editor?.putInt("totalHp", totalHp)
        editor?.putInt("damage", damage)
        editor?.putInt("passiveDamage", passiveDamage)
        editor?.putInt("wallet", wallet)
        editor?.putInt("lvl", lvl)
        editor?.putInt("lt", lastTexture)
        editor?.putInt("maxHp", maxHp)
        editor?.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    fun delAll() {
        val editor = preff?.edit()
        editor?.clear()
        editor?.apply()
    }
}





