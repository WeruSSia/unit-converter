package com.werussia.unit_converter

enum class PressureUnit : ConverterUnit {
    BAR {
        override val factor :Double= 1.0
        override val alias = "bar"
    },
    PASCAL {
        override val factor :Double= 100_000.0
        override val alias = "Pa"
    },
    MEGAPASCAL {
        override val factor :Double= 0.1
        override val alias = "mPa"
    },
    ATMOSPHERE {
        override val factor:Double = 0.986923
        override val alias = "atmosphere"
    },
    TORR {
        override val factor :Double= 750.062
        override val alias = "torr"
    };

    companion object : AliasesHolder {
        override fun getAliases(): List<String> {
            return values().map { it.alias }
        }
    }
}