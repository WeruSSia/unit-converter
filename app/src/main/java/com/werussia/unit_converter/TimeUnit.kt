package com.werussia.unit_converter

enum class TimeUnit : ConverterUnit {
    MILLISECOND {
        override val factor: Double = 3_600_000.0
        override val alias: String = "ms"
    },
    SECOND {
        override val factor: Double = 3_600.0
        override val alias: String = "s"
    },
    MINUTE {
        override val factor: Double = 60.0
        override val alias: String = "min"
    },
    HOUR {
        override val factor: Double = 1.0
        override val alias: String = "h"
    },
    DAY {
        override val factor: Double = 0.0416667
        override val alias: String = "day"
    },
    WEEK {
        override val factor: Double = 0.00595238
        override val alias: String = "week"
    },
    MONTH {
        override val factor: Double = 0.00136986
        override val alias: String = "month"
    },
    YEAR {
        override val factor: Double = 0.000114155
        override val alias: String = "year"
    },
    DECADE {
        override val factor: Double = 0.000011416
        override val alias: String = "decade"
    },
    CENTURY {
        override val factor: Double = 0.0000011416
        override val alias: String = "century"
    };

    companion object : AliasesHolder {
        override fun getAliases(): List<String> {
            return values().map { it.alias }
        }
    }
}