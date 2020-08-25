package com.werussia.unit_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val lengthUnits = arrayOf("mm", "cm", "m", "km")
    private val weightUnits = arrayOf("mg", "g", "kg", "t")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromDropdownMenu: AutoCompleteTextView = findViewById(R.id.from_unit_dropdown)
        val toDropdownMenu: AutoCompleteTextView = findViewById(R.id.to_unit_dropdown)

        setDropdownMenu(fromDropdownMenu)
        setDropdownMenu(toDropdownMenu)

        val inputEditText: EditText = findViewById(R.id.amount_input)
        val outputTextView: TextView = findViewById(R.id.output_text_view)
        val convertButton: Button = findViewById(R.id.convert_button)

        setButton(convertButton, fromDropdownMenu, toDropdownMenu, inputEditText, outputTextView)

        inputEditText.addTextChangedListener(textWatcher)
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

    private fun setDropdownMenu(dropdownMenu: AutoCompleteTextView) {
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            lengthUnits + weightUnits
        )

        dropdownMenu.setAdapter(arrayAdapter)
    }

    private fun setButton(
        convertButton: Button,
        fromDropdownMenu: AutoCompleteTextView,
        toDropdownMenu: AutoCompleteTextView,
        inputEditText: EditText,
        outputTextView: TextView
    ) {
        convertButton.isEnabled = false
        convertButton.setOnClickListener {
            Converter(
                lengthUnits,
                weightUnits,
                fromDropdownMenu.text.toString(),
                toDropdownMenu.text.toString(),
                inputEditText.text.toString().toDouble(),
                outputTextView
            ).convert()
        }
    }
}
