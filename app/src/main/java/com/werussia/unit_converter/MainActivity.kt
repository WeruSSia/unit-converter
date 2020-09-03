package com.werussia.unit_converter

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unit_choice_dropdown_menu.setText(UnitType.getAliases().first())
        setDropdownMenu(unit_choice_dropdown_menu, UnitType)

        input_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
        setDropdownMenu(input_unit_dropdown_menu, LengthUnit)

        output_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
        setDropdownMenu(output_unit_dropdown_menu, LengthUnit)

        setUnitChoice()

        setConvertButton()

        input_value.addTextChangedListener(textWatcher)
    }

    private fun setUnitChoice() {
        unit_choice_dropdown_menu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                when (parent.getItemAtPosition(position).toString()) {
                    "Length" -> {
                        input_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
                        output_unit_dropdown_menu.setText(LengthUnit.getAliases().first())
                        setDropdownMenu(input_unit_dropdown_menu, LengthUnit)
                        setDropdownMenu(output_unit_dropdown_menu, LengthUnit)
                    }
                    "Weight" -> {
                        input_unit_dropdown_menu.setText(WeightUnit.getAliases().first())
                        output_unit_dropdown_menu.setText(WeightUnit.getAliases().first())
                        setDropdownMenu(input_unit_dropdown_menu, WeightUnit)
                        setDropdownMenu(output_unit_dropdown_menu, WeightUnit)
                    }
                }
            }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            convert_button.isEnabled = s.toString() != ""
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

    private fun setConvertButton() {
        convert_button.isEnabled = false
        convert_button.setOnClickListener {
            result_text_view.text = Converter(
                input_unit_dropdown_menu.text.toString(),
                output_unit_dropdown_menu.text.toString(),
                input_value.text.toString().toDouble()
            ).convert()
        }
    }
}
