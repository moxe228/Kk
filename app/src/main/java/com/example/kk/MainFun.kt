package com.example.kk

import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

public fun whoNext(list: List<Int>): Int {
    return list.random()
}

public fun passiveDamage(
    hpMob: Int,
    hpBoss: Int,
    revardMob: Int,
    revardBoss: Int,
    texturePack: List<Int>,
    enemy: ImageButton,
    Hp: ProgressBar,
    money: TextView,
    stringHp: TextView
) {
    val handler = Handler()
    handler.postDelayed(object : Runnable {
        override fun run() {
            onClick(hpMob, hpBoss, revardMob, revardBoss, getPassiveDamage(), texturePack, enemy, Hp, money, stringHp)
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
        if(totalHp <= 0) boss++
    }
    if(totalHp<= 0)money.text = "$wallet"
    Hp.progress = totalHp
    stringHp.text = "$totalHp"
}

private fun fight(
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
        wallet += revardMob
        totalHp = hpMob
        Hp.max = hpMob
        enemy.setImageResource(textures)
    }
}

public fun getPassiveDamage(): Int{
    return passiveDamage
}

public fun equip(S1: Boolean, S2: Boolean, S3: Boolean, BS1: Button, BS2: Button, BS3: Button) {
    equipS1 = S1
    equipS2 = S2
    equipS3 = S3
    BS1.isEnabled = !S1
    BS2.isEnabled = !S2
    BS3.isEnabled = !S3
}