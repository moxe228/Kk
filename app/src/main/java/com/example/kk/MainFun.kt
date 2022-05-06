package com.example.kk

import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

private var hpMob = 0
private var revardMob = 0
private var hpBoss = 0
private var revardBoss = 0
public var lastTexture = 0

public fun whoNext(boss: Boolean): Int{
    val enemyOne = listOf(R.drawable.s, R.drawable.smile, R.drawable.qq)
    val enemtTwo = listOf(R.drawable.unnamed)
    if (!boss) {
        when{
            lvl in 0..1 -> return enemyOne.random()
            lvl in 2..3 -> return enemtTwo.random()
        }
    } else {
        when{
            lvl in 0..1 -> return R.drawable.boss
            lvl in 2..3 -> return R.drawable.boss
        }
    }
    return 0
}


public fun hpAndRevard(){
    when{
        lvl in 0..1 -> {
            hpMob = 10
            hpBoss = 20
            revardMob = 1
            revardBoss = 10
        }
        lvl in 2..3 -> {
            hpMob = 15
            hpBoss = 30
            revardMob = 2
            revardBoss = 20
        }
    }
}

public fun passiveDamage(
    enemy: ImageButton,
    Hp: ProgressBar,
    money: TextView,
    stringHp: TextView
) {
    val handler = Handler()
    handler.postDelayed(object : Runnable {
        override fun run() {
            hpAndRevard()
            onClick(getPassiveDamage(), enemy, Hp, money, stringHp)
            handler.postDelayed(this, 1000)
        }
    }, 0)
}

public fun onClick(
    damage: Int,
    enemy: ImageButton,
    Hp: ProgressBar,
    money: TextView,
    stringHp: TextView
) {
    lastTexture = if(boss == 5) whoNext(true) else whoNext(false)
    if (boss == 5) {
        fight(damage, hpBoss, revardBoss, Hp, enemy, lastTexture)
        if (totalHp <= 0) {
            boss = 0
            lvl++
        }
    } else {
        fight(damage, hpMob, revardMob, Hp, enemy, lastTexture)
        if (totalHp <= 0) boss++
    }
    if (totalHp <= 0) money.text = "$wallet"
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


private fun getPassiveDamage(): Int {
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