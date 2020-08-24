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

        val fromSpinner: Spinner = findViewById(R.id.from_unit_spinner)
        val toSpinner: Spinner = findViewById(R.id.to_unit_spinner)

        setSpinner(fromSpinner)
        setSpinner(toSpinner)

        val inputEditText: EditText = findViewById(R.id.amount_input)
        val outputTextView: TextView = findViewById(R.id.output_text_view)
        val convertButton: Button = findViewById(R.id.convert_button)

        setButton(convertButton, fromSpinner, toSpinner, inputEditText, outputTextView)

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

    private fun setSpinner(spinner: Spinner) {
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            lengthUnits + weightUnits
        )
        with(spinner)
        {
            adapter = arrayAdapter
            setSelection(0, false)
        }
    }

    private fun setButton(
        convertButton: Button,
        fromSpinner: Spinner,
        toSpinner: Spinner,
        inputEditText: EditText,
        outputTextView: TextView
    ) {
        convertButton.isEnabled = false
        convertButton.setOnClickListener {
            Converter(
                lengthUnits,
                weightUnits,
                fromSpinner.selectedItem.toString(),
                toSpinner.selectedItem.toString(),
                inputEditText.text.toString().toDouble(),
                outputTextView
            ).convert()
        }
    }
}
