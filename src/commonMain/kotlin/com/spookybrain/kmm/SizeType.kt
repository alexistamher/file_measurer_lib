package com.spookybrain.kmm

private const val DIVIDER_SIZE = 1000.0

enum class SizeType {
    BYTE {
        override fun times(): Double = 1.0
        override fun sizeName(): String = "B"
    },
    KILOBYTE {
                override fun times(): Double = DIVIDER_SIZE
        override fun sizeName(): String = "KB"
    },
    MEGABYTE {
        override fun times(): Double = DIVIDER_SIZE.times(DIVIDER_SIZE)
        override fun sizeName(): String = "MB"
    },
    GIGABYTE {
        override fun times(): Double = DIVIDER_SIZE.times(DIVIDER_SIZE).times(DIVIDER_SIZE)
        override fun sizeName(): String = "GB"
    };

    internal abstract fun times(): Double

    abstract fun sizeName(): String
}