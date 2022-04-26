package com.example.kk

import android.os.Handler
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

public var boss: Int = 0
public var totalHp: Int = 0
public var damage: Int = 1
public var passiveDamage: Int = 0
public var wallet: Int = 0

public fun whoNext(list: List<Int>): Int {
    return list.random()
}

public fun passiveDamage(
    hpMob: Int,
    hpBoss: Int,
    revardMob: Int,
    revardBoss: Int,
    passiveDamage: Int,
    texturePack: List<Int>,
    enemy: ImageButton,
    Hp: ProgressBar,
    money: TextView,
    stringHp: TextView
) {
    val handler = Handler()
    handler.postDelayed(object : Runnable {
        override fun run() {
            onClick(hpMob, hpBoss, revardMob, revardBoss, passiveDamage, texturePack, enemy, Hp, money, stringHp)
            handler.postDelayed(this, 1000)
        }
    }, 0)
}

public fun onClick(
    hpMob: Int,
    hpBoss: Int,
    revardMob: Int,
    revardBoss: Int,
    damage: Int,
    texturePack: List<Int>,
    enemy: ImageButton,
    Hp: ProgressBar,
    money: TextView,
    stringHp: TextView
) {
    if (boss == 5) {
        fight(damage, hpBoss, revardBoss, Hp, enemy, R.drawable.boss)
        if (totalHp <= 0) boss = 0
    } else {
        fight(damage, hpMob, revardMob, Hp, enemy, whoNext(texturePack))
        if (totalHp <= 0) boss++
    }
    if (totalHp <= 0) money.text = "$wallet"
    Hp.progress = totalHp
    stringHp.text = "$totalHp"
}

public fun fight(
    damage: Int,
    hpMob: Int,
    revardMob: Int,
    Hp: ProgressBar,
    enemy: ImageButton,
    textures: Int
) {
    if (totalHp > 0) {
        totalHp -= damage
    } else {
        totalHp = hpMob
        Hp.max = hpMob
        wallet += revardMob
        enemy.setImageResource(textures)
    }
}