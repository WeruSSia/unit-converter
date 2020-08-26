package com.werussia.unit_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val lengthUnits = arrayOf("mm", "cm", "m", "km")
    private val weightUnits = arrayOf("mg", "g", "kg", "t")
    private val unitTypes = arrayOf("Length", "Weight")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unitChoiceDropdownMenu: AutoCompleteTextView =
            findViewById(R.id.unit_choice_dropdown_menu)
        unitChoiceDropdownMenu.setText(unitTypes[0])
        setDropdownMenu(unitChoiceDropdownMenu, unitTypes)

        val inputUnitDropdownMenu: AutoCompleteTextView =
            findViewById(R.id.input_unit_dropdown_menu)
        inputUnitDropdownMenu.setText(lengthUnits[0])
        setDropdownMenu(inputUnitDropdownMenu, lengthUnits)

        val outputUnitDropdownMenu: AutoCompleteTextView =
            findViewById(R.id.output_unit_dropdown_menu)
        outputUnitDropdownMenu.setText(lengthUnits[0])
        setDropdownMenu(outputUnitDropdownMenu, lengthUnits)

        setUnitChoice(unitChoiceDropdownMenu, inputUnitDropdownMenu, outputUnitDropdownMenu)

        val inputEditText: EditText = findViewById(R.id.input_value)
        val outputTextView: TextView = findViewById(R.id.result_text_view)
        val convertButton: Button = findViewById(R.id.convert_button)

        setButton(
            convertButton,
            inputUnitDropdownMenu,
            outputUnitDropdownMenu,
            inputEditText,
            outputTextView
        )

        inputEditText.addTextChangedListener(textWatcher)
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
                    }
                    "Weight" -> {
                        inputUnitDropdownMenu.setText(weightUnits[0])
                        outputUnitDropdownMenu.setText(weightUnits[0])
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
        val arrayAdapter = ArrayAdapter<String>(
            this,
            R.layout.dropdown_menu_popup_item,
            list
        )
        dropdownMenu.setAdapter(arrayAdapter)
    }

    private fun setButton(
        convertButton: Button,
        inputUnitDropdownMenu: AutoCompleteTextView,
        outputUnitDropdownMenu: AutoCompleteTextView,
        inputEditText: EditText,
        outputTextView: TextView
    ) {
        convertButton.isEnabled = false
        convertButton.setOnClickListener {
            Converter(
                inputUnitDropdownMenu.text.toString(),
                outputUnitDropdownMenu.text.toString(),
                inputEditText.text.toString().toDouble(),
                outputTextView
            ).convert()
        }
    }
}
