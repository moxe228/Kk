package com.example.kk

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class GlobalData {
    companion object {
        public var boss: Int = 0
        public var totalHp: Int = 1
        public var damage: Int = 1
        public var passiveDamage: Int = 0
        public var wallet: Int = 0
        public var lvl: Int = 0

        public var lastTexture = 0
        public var maxHp = 1
        public var endGame = false
    }
}

public class MainActivity : AppCompatActivity() {
    public var pref1: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref1 = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        if (GlobalData.endGame) {
            delAll()
        }
        GlobalData.boss = pref1?.getInt("boss", 0)!!
        GlobalData.totalHp = pref1?.getInt("totalHp", 0)!!
        GlobalData.damage = pref1?.getInt("damage", 1)!!
        GlobalData.passiveDamage = pref1?.getInt("passiveDamage", 0)!!
        GlobalData.wallet = pref1?.getInt("wallet", 0)!!
        GlobalData.lvl = pref1?.getInt("lvl", 0)!!
        GlobalData.lastTexture = pref1?.getInt("lt", GlobalData.lastTexture)!!
        GlobalData.maxHp = pref1?.getInt("maxHp", GlobalData.maxHp)!!

        val weapon: Button = findViewById(R.id.weapon)
        val partners: Button = findViewById(R.id.partners)
        val money: TextView = findViewById(R.id.money)
        val enemy: ImageButton = findViewById(R.id.enemy)
        val Hp: ProgressBar = findViewById(R.id.progressBarHp)
        val stringHp: TextView = findViewById(R.id.stringHp)

        hpAndReward()
        money.text = "${GlobalData.wallet}"
        Hp.max = GlobalData.maxHp
        enemy.setImageResource(GlobalData.lastTexture)
        passiveDamage(enemy, Hp, money, stringHp)

        enemy.setOnClickListener {
            if (GlobalData.lvl > 5) {
                GlobalData.endGame = true
                this.finishAffinity()
            }
            GlobalData.endGame = false
            hpAndReward()
            stringHp.text = "${GlobalData.totalHp}"
            Hp.progress = GlobalData.totalHp
            onClick(GlobalData.damage, enemy, Hp, money, stringHp)
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
        val editor = pref1?.edit()
        editor?.putInt("boss", GlobalData.boss)
        editor?.putInt("totalHp", GlobalData.totalHp)
        editor?.putInt("damage", GlobalData.damage)
        editor?.putInt("passiveDamage", GlobalData.passiveDamage)
        editor?.putInt("wallet", GlobalData.wallet)
        editor?.putInt("lvl", GlobalData.lvl)
        editor?.putInt("lt", GlobalData.lastTexture)
        editor?.putInt("maxHp", GlobalData.maxHp)
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
        val editor = pref1?.edit()
        editor?.clear()
        editor?.apply()
    }
}





