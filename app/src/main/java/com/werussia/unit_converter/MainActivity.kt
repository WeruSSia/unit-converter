package com.werussia.unit_converter

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private var selectedUnitType: UnitType = UnitType.LENGTH
    private var selectedInputUnitIndex: Int = 0
    private var selectedOutputUnitIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unit_choice_dropdown_menu.setText(UnitType.getAliases().first())
        setDropdownMenu(unit_choice_dropdown_menu, UnitType)

        input_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
        setDropdownMenu(input_unit_dropdown_menu, LengthUnit)

        output_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
        setDropdownMenu(output_unit_dropdown_menu, LengthUnit)

        setUnitsChoice()
        setInputUnitListener()
        setOutputUnitListener()

        input_value.addTextChangedListener(textWatcher)
    }

    private fun setUnitsChoice() {
        unit_choice_dropdown_menu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                when (parent.getItemAtPosition(position).toString()) {
                    UnitType.LENGTH.alias -> {
                        selectedUnitType = UnitType.LENGTH
                        input_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
                        output_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
                        setDropdownMenu(input_unit_dropdown_menu, LengthUnit)
                        setDropdownMenu(output_unit_dropdown_menu, LengthUnit)
                        if (input_value.text.toString() != "") {
                            result_text_view.text = convert().toString()
                        }
                    }
                    UnitType.WEIGHT.alias -> {
                        selectedUnitType = UnitType.WEIGHT
                        input_unit_dropdown_menu.setText(WeightUnit.getAliases().first())
                        output_unit_dropdown_menu.setText(WeightUnit.getAliases().first())
                        setDropdownMenu(input_unit_dropdown_menu, WeightUnit)
                        setDropdownMenu(output_unit_dropdown_menu, WeightUnit)
                        if (input_value.text.toString() != "") {
                            result_text_view.text = convert().toString()
                        }
                    }
                    UnitType.PRESSURE.alias -> {
                        selectedUnitType = UnitType.PRESSURE
                        input_unit_dropdown_menu.setText(PressureUnit.getAliases().first())
                        output_unit_dropdown_menu.setText(PressureUnit.getAliases().first())
                        setDropdownMenu(input_unit_dropdown_menu, PressureUnit)
                        setDropdownMenu(output_unit_dropdown_menu, PressureUnit)
                        if (input_value.text.toString() != "") {
                            result_text_view.text = convert().toString()
                        }
                    }
                }
            }
    }

    private fun setInputUnitListener() {
        input_unit_dropdown_menu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedInputUnitIndex = position
                if (input_value.text.toString() != "") {
                    result_text_view.text = convert().toString()
                }
            }
    }

    private fun setOutputUnitListener() {
        output_unit_dropdown_menu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                selectedOutputUnitIndex = position
                if (input_value.text.toString() != "") {
                    result_text_view.text = convert().toString()
                }
            }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (input_value.text.toString() != "") {
                result_text_view.text = convert().toString()
            }
        }
    }

    private fun setDropdownMenu(dropdownMenu: AutoCompleteTextView, list: AliasesHolder) {
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            list.getAliases().toTypedArray()
        )
        dropdownMenu.setAdapter(arrayAdapter)
    }

    private fun convert(): Double {
        return when (selectedUnitType) {
            UnitType.LENGTH -> input_value.text.toString()
                .toDouble() * LengthUnit.values()[selectedOutputUnitIndex].factor / LengthUnit.values()[selectedInputUnitIndex].factor
            UnitType.WEIGHT -> input_value.text.toString()
                .toDouble() * WeightUnit.values()[selectedOutputUnitIndex].factor / WeightUnit.values()[selectedInputUnitIndex].factor
            UnitType.PRESSURE -> input_value.text.toString()
                .toDouble() * PressureUnit.values()[selectedOutputUnitIndex].factor / PressureUnit.values()[selectedInputUnitIndex].factor
        }
    }
}
