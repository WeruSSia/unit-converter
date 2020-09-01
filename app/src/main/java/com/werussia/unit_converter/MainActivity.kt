package com.werussia.unit_converter

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private val lengthUnits = arrayOf("mm", "cm", "m", "km")
    private val weightUnits = arrayOf("mg", "g", "kg", "t")
    private val unitTypes = arrayOf("Length", "Weight")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unitChoiceDropdownMenu = unit_choice_dropdown_menu
        unitChoiceDropdownMenu.setText(unitTypes[0])
        setDropdownMenu(unitChoiceDropdownMenu, unitTypes)

        val inputUnitDropdownMenu = input_unit_dropdown_menu
        inputUnitDropdownMenu.setText(lengthUnits[0])
        setDropdownMenu(inputUnitDropdownMenu, lengthUnits)

        val outputUnitDropdownMenu = output_unit_dropdown_menu
        outputUnitDropdownMenu.setText(lengthUnits[0])
        setDropdownMenu(outputUnitDropdownMenu, lengthUnits)

        setUnitChoice(unitChoiceDropdownMenu, inputUnitDropdownMenu, outputUnitDropdownMenu)

        setConvertButton(
            inputUnitDropdownMenu,
            outputUnitDropdownMenu
        )

        input_value.addTextChangedListener(textWatcher)
    }

    private fun setUnitChoice(
        unitChoiceDropdownMenu: AutoCompleteTextView,
        inputUnitDropdownMenu: AutoCompleteTextView,
        outputUnitDropdownMenu: AutoCompleteTextView
    ) {
        unitChoiceDropdownMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                when (parent.getItemAtPosition(position).toString()) {
                    "Length" -> {
                        inputUnitDropdownMenu.setText(lengthUnits[0])
                        outputUnitDropdownMenu.setText(lengthUnits[0])
                        setDropdownMenu(inputUnitDropdownMenu, lengthUnits)
                        setDropdownMenu(outputUnitDropdownMenu, lengthUnits)
                    }
                    "Weight" -> {
                        inputUnitDropdownMenu.setText(weightUnits[0])
                        outputUnitDropdownMenu.setText(weightUnits[0])
                        setDropdownMenu(inputUnitDropdownMenu, weightUnits)
                        setDropdownMenu(outputUnitDropdownMenu, weightUnits)
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

    private fun setDropdownMenu(dropdownMenu: AutoCompleteTextView, list: Array<String>) {
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            list
        )
        dropdownMenu.setAdapter(arrayAdapter)
    }

    private fun setConvertButton(
        inputUnitDropdownMenu: AutoCompleteTextView,
        outputUnitDropdownMenu: AutoCompleteTextView
    ) {
        convert_button.isEnabled = false
        convert_button.setOnClickListener {
            result_text_view.text = Converter(
                inputUnitDropdownMenu.text.toString(),
                outputUnitDropdownMenu.text.toString(),
                input_value.text.toString().toDouble()
            ).convert()
        }
    }
}
