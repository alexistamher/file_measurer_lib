package com.spookybrain.kmm

import kotlin.test.Test
import kotlin.test.assertEquals

class SizeTypeTest {
    @Test
    fun should_return_1() {
        val type = SizeType.BYTE
        val times = type.times()
        assertEquals(type.sizeName(),"B")
        assertEquals(times, 1.0)
    }

    @Test
    fun should_return_1_000() {
        val type = SizeType.KILOBYTE
        val times = type.times()
        assertEquals(type.sizeName(),"KB")
        assertEquals(times, 1000.0)
    }

    @Test
    fun should_return_100_000() {
        val type = SizeType.MEGABYTE
        val times = type.times()
        assertEquals(type.sizeName(),"MB")
        assertEquals(times, 1000000.0)
    }

    @Test
    fun should_return_1_000_000_000() {
        val type = SizeType.GIGABYTE
        val times = type.times()
        assertEquals(type.sizeName(),"GB")
        assertEquals(times, 1000000000.0)
    }
}