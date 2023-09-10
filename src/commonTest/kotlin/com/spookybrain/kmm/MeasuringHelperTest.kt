package com.spookybrain.kmm

import kotlin.test.Test
import kotlin.test.assertEquals

class MeasuringHelperTest {
    private val sample = 1.0

    @Test
    fun should_coverts_sample_to_byte() {
        val result = toSizeType(sample, SizeType.BYTE)
        assertEquals(result, 1.0)
    }

    @Test
    fun should_coverts_sample_to_kilobyte() {
        val result = toSizeType(sample, SizeType.KILOBYTE)
        assertEquals(result, 0.001)
    }

    @Test
    fun should_coverts_sample_to_megabyte() {
        val result = toSizeType(sample, SizeType.MEGABYTE)
        assertEquals(result, 0.000001)
    }

    @Test
    fun should_coverts_sample_to_gigabyte() {
        val result = toSizeType(sample, SizeType.GIGABYTE)
        assertEquals(result, 0.000000001)
    }
}