package com.example.kk

import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class Test {
    @Test
    fun test1() {
        lvl = 1
        val textur = whoNext(false)
        val expectedTexture = listOf(R.drawable.e11, R.drawable.e12, R.drawable.e13)
        assertEquals(true, textur in expectedTexture)
    }

    @Test
    fun test2() {
        lvl = 1
        val textur = whoNext(true)
        val expectedTexture = listOf(R.drawable.b11)
        assertEquals(true, textur in expectedTexture)
    }

    @Test
    fun test3() {
        lvl = 3
        val textur = whoNext(false)
        val expectedTexture = listOf(R.drawable.s, R.drawable.smile, R.drawable.qq)
        assertEquals(true, textur in expectedTexture)
    }

    @Test
    fun test4() {
        lvl = 3
        val textur = whoNext(true)
        val expectedTexture = listOf(R.drawable.boss)
        assertEquals(true, textur in expectedTexture)
    }

    @Test
    fun test5() {
        lvl = 5
        val textur = whoNext(false)
        val expectedTexture = listOf(R.drawable.e31, R.drawable.e32, R.drawable.e33)
        assertEquals(true, textur in expectedTexture)
    }

    @Test
    fun test6() {
        lvl = 5
        val textur = whoNext(true)
        val expectedTexture = listOf(R.drawable.unnamed)
        assertEquals(true, textur in expectedTexture)
        lvl = 0
    }
}
