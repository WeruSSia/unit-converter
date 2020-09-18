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

        chooseUnitType()
        setInputUnitListener()
        setOutputUnitListener()

        input_value.addTextChangedListener(textWatcher)
    }

    private fun chooseUnitType() {
        unit_choice_dropdown_menu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                when (parent.getItemAtPosition(position).toString()) {
                    UnitType.LENGTH.alias -> {
                        setUnitType(UnitType.LENGTH,LengthUnit)
                    }
                    UnitType.WEIGHT.alias -> {
                        setUnitType(UnitType.WEIGHT, WeightUnit)
                    }
                    UnitType.PRESSURE.alias -> {
                        setUnitType(UnitType.PRESSURE,PressureUnit)
                    }
                    UnitType.TIME.alias -> {
                        setUnitType(UnitType.TIME,TimeUnit)
                    }
                }
            }
    }

    private fun setUnitType(unitType: UnitType, aliasesHolder: AliasesHolder){
        selectedUnitType = unitType
        selectedInputUnitIndex = 0
        selectedOutputUnitIndex = 0
        input_unit_dropdown_menu.setText(aliasesHolder.getAliases().first())
        output_unit_dropdown_menu.setText(aliasesHolder.getAliases().first())
        setDropdownMenu(input_unit_dropdown_menu,aliasesHolder)
        setDropdownMenu(output_unit_dropdown_menu,aliasesHolder)
        if (input_value.text.toString() != "") {
            result_text_view.text = convert().toString()
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
            UnitType.TIME -> input_value.text.toString()
                .toDouble() * TimeUnit.values()[selectedOutputUnitIndex].factor / TimeUnit.values()[selectedInputUnitIndex].factor
        }
    }
}
