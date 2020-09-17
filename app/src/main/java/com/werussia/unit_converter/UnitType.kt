package com.werussia.unit_converter

enum class UnitType(val alias: String) {
    LENGTH("Length"),
    WEIGHT("Weight"),
    PRESSURE("Pressure");

    companion object : AliasesHolder {
        override fun getAliases(): List<String> {
            return values().map { it.alias }
        }
    }
}