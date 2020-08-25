package com.werussia.unit_converter

import android.widget.TextView

class Converter(
    private val lengthUnits: Array<String>,
    private val weightUnits: Array<String>,
    private val inputUnit: String,
    private val outputUnit: String,
    private val inputValue: Double,
    private val resultTextView: TextView
) {
    fun convert() {
        var outputValue = 0.0
        if (lengthUnits.contains(inputUnit) && lengthUnits.contains(outputUnit)) {
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
            }
            resultTextView.text = "$outputValue $outputUnit"
        } else if (weightUnits.contains(inputUnit) && weightUnits.contains(outputUnit)) {
            when (inputUnit) {
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
            resultTextView.text = "$outputValue $outputUnit"
        } else if (inputUnit == "") {
            resultTextView.text = "Choose input unit!"
        } else if (outputUnit == "") {
            resultTextView.text = "Choose output unit!"
        } else {
            resultTextView.text = "Cannot convert from $inputUnit to $outputUnit!"
        }
    }
}