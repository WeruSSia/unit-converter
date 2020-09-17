package com.werussia.unit_converter

enum class WeightUnit : ConverterUnit {
    MILLIGRAM {
        override val factor: Double = 1.0
        override val alias = "mg"
    },
    GRAM {
        override val factor: Double = 1_000.0
        override val alias = "g"
    },
    KILOGRAM {
        override val factor: Double = 1_000_000.0
        override val alias = "kg"
    },
    TONNE {
        override val factor: Double = 1_000_000_000.0
        override val alias = "t"
    },
    POUND {
        override val factor: Double = 453_592.0
        override val alias = "lb"
    };

    companion object : AliasesHolder {
        override fun getAliases(): List<String> {
            return values().map { it.alias }
        }
    }
}