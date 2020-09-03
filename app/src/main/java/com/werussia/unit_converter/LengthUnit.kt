package com.werussia.unit_converter

enum class LengthUnit : ConverterUnit {
    MILLIMETER {
        override val factor: Double = 1.0
        override val alias = "mm"
    },
    CENTIMETER {
        override val factor: Double = 10.0
        override val alias = "cm"
    },
    METER {
        override val factor: Double = 1_000.0
        override val alias = "m"
    },
    KILOMETER {
        override val factor: Double = 1_000_000.0
        override val alias = "km"
    },
    FOOT {
        override val factor: Double = 304.8
        override val alias = "ft"
    };

    companion object : AliasesHolder {
        override fun getAliases(): List<String> {
            return values().map { it.alias }
        }
    }
}