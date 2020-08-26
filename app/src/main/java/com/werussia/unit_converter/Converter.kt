package com.werussia.unit_converter

import android.widget.TextView

class Converter(
    private val inputUnit: String,
    private val outputUnit: String,
    private val inputValue: Double
) {
    fun convert() : String{
        var outputValue = 0.0
        when (inputUnit) {
            "mm" -> when (outputUnit) {
                "mm" -> outputValue = inputValue
                "cm" -> outputValue = inputValue / 10
                "m" -> outputValue = inputValue / 1000
                "km" -> outputValue = inputValue / 1000000
            }
            "cm" -> when (outputUnit) {
                "mm" -> outputValue = inputValue * 10
                "cm" -> outputValue = inputValue
                "m" -> outputValue = inputValue / 100
                "km" -> outputValue = inputValue / 100000
            }
            "m" -> when (outputUnit) {
                "mm" -> outputValue = inputValue * 1000
                "cm" -> outputValue = inputValue * 100
                "m" -> outputValue = inputValue
                "km" -> outputValue = inputValue / 1000
            }
            "km" -> when (outputUnit) {
                "mm" -> outputValue = inputValue * 1000000
                "cm" -> outputValue = inputValue * 100000
                "m" -> outputValue = inputValue * 1000
                "km" -> outputValue = inputValue
            }
            "mg" -> when (outputUnit) {
                "mg" -> outputValue = inputValue
                "g" -> outputValue = inputValue / 1000
                "kg" -> outputValue = inputValue / 1000000
                "t" -> outputValue = inputValue / 1000000000
            }
            "g" -> when (outputUnit) {
                "mg" -> outputValue = inputValue * 1000
                "g" -> outputValue = inputValue
                "kg" -> outputValue = inputValue / 1000
                "t" -> outputValue = inputValue / 1000000
            }
            "kg" -> when (outputUnit) {
                "mg" -> outputValue = inputValue * 1000000
                "g" -> outputValue = inputValue * 1000
                "kg" -> outputValue = inputValue
                "t" -> outputValue = inputValue / 1000
            }
            "t" -> when (outputUnit) {
                "mg" -> outputValue = inputValue * 1000000000
                "g" -> outputValue = inputValue * 1000000
                "kg" -> outputValue = inputValue * 1000
                "t" -> outputValue = inputValue
            }
        }
        return "$outputValue $outputUnit"

    }
}