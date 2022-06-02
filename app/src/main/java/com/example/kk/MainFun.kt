package com.example.kk

import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

private var hpMob = 0
private var rewardMob = 0
private var hpBoss = 0
private var rewardBoss = 0


public fun whoNext(boss: Boolean): Int{
    val enemyOne = listOf(R.drawable.e11, R.drawable.e12, R.drawable.e13)
    val enemtTwo = listOf(R.drawable.s, R.drawable.smile, R.drawable.qq)
    val enemyThree = listOf(R.drawable.e31, R.drawable.e32, R.drawable.e33)
    if (!boss) {
        when{
            GlobalData.lvl in 0..1 -> return enemyOne.random()
            GlobalData.lvl in 2..3 -> return enemtTwo.random()
            GlobalData.lvl in 4..5 -> return enemyThree.random()
            GlobalData.lvl > 5 -> GlobalData.endGame = true
        }
    } else {
        when{
            GlobalData.lvl in 0..1 -> return R.drawable.b11
            GlobalData.lvl in 2..3 -> return R.drawable.boss
            GlobalData.lvl in 4..5 -> return R.drawable.unnamed
            GlobalData.lvl > 5 -> GlobalData.endGame = true
        }
    }
    return 0
}

public fun hpAndReward(){
    when{
        GlobalData.lvl in 0..1 -> {
            hpMob = 10
            hpBoss = 20
            rewardMob = 1
            rewardBoss = 10
        }
        GlobalData.lvl in 2..3 -> {
            hpMob = 15
            hpBoss = 30
            rewardMob = 2
            rewardBoss = 20
        }
        GlobalData.lvl in 4..5 -> {
            hpMob = 20
            hpBoss = 40
            rewardMob = 3
            rewardBoss = 30
        }
        GlobalData.lvl > 5 -> GlobalData.endGame = true
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
            hpAndReward()
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
    if (GlobalData.boss == 5) {
        fight(damage, hpBoss, Hp, enemy, whoNext(true))
        if (GlobalData.totalHp <= 0) {
            GlobalData.wallet += rewardBoss
            GlobalData.boss = 0
            GlobalData.lvl++
        }
    } else {
        fight(damage, hpMob, Hp, enemy, whoNext(false))
        if (GlobalData.totalHp <= 0) {
            GlobalData.wallet += rewardMob
            GlobalData.boss++
        }
    }
    if (GlobalData.totalHp <= 0) money.text = "${GlobalData.wallet}"
    Hp.progress =  GlobalData.totalHp
    stringHp.text = "${GlobalData.totalHp}"
}

private fun fight(
    damage: Int,
    hpMob: Int,
    Hp: ProgressBar,
    enemy: ImageButton,
    textures: Int
) {
    if (GlobalData.totalHp > 0) {
        GlobalData.totalHp -= damage
    } else {
        GlobalData.maxHp = hpMob
        GlobalData.totalHp = hpMob
        Hp.max = hpMob
        GlobalData.lastTexture = textures
        enemy.setImageResource(textures)
    }
}


private fun getPassiveDamage(): Int {
    return GlobalData.passiveDamage
}

public fun equip(S1: Boolean, S2: Boolean, S3: Boolean, BS1: Button, BS2: Button, BS3: Button) {
    equipS1 = S1
    equipS2 = S2
    equipS3 = S3
    BS1.isEnabled = !S1
    BS2.isEnabled = !S2
    BS3.isEnabled = !S3
}

