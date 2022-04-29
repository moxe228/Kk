package com.example.kk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.collections.listOf as listOf

public var boss: Int = 0
public var totalHp: Int = 0
public var damage: Int = 1
public var passiveDamage: Int = 0
public var wallet: Int = 100

public class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enemyOne = listOf(R.drawable.s, R.drawable.smile, R.drawable.qq)

        val weapon: Button = findViewById(R.id.weapon)
        val partners: Button = findViewById(R.id.partners)

        val money: TextView = findViewById(R.id.money)
        val enemy: ImageButton = findViewById(R.id.enemy)
        val Hp: ProgressBar = findViewById(R.id.progressBarHp)
        val stringHp: TextView = findViewById(R.id.stringHp)


        money.text = "$wallet"

        passiveDamage(10, 20, 1, 10, enemyOne, enemy, Hp, money, stringHp)

        enemy.setOnClickListener {
            stringHp.text = "$totalHp"
            Hp.progress = totalHp
            onClick(10, 20, 1, 10, damage, enemyOne, enemy, Hp, money, stringHp)
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

}





