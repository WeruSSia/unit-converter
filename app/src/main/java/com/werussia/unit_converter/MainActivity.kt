package com.werussia.unit_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var inputEditText: EditText
    lateinit var outputTextView: TextView
    lateinit var convertButton: Button

    private val lengthUnits = arrayOf("mm", "cm", "m", "km")
    private val weightUnits = arrayOf("mg", "g", "kg", "t")
    val INPUT_UNIT_SPINNER_ID = 1
    val OUTPUT_UNIT_SPINNER_ID = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromSpinner: Spinner = findViewById(R.id.from_unit_spinner)
        fromSpinner.id = INPUT_UNIT_SPINNER_ID
        val toSpinner: Spinner = findViewById(R.id.to_unit_spinner)
        toSpinner.id = OUTPUT_UNIT_SPINNER_ID

        setSpinner(fromSpinner)
        setSpinner(toSpinner)

        inputEditText = findViewById(R.id.amount_input)
        outputTextView = findViewById(R.id.output_text_view)
        convertButton = findViewById(R.id.convert_button)

        convert_button.isEnabled = false

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

        inputEditText.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            convert_button.isEnabled = !s.toString().equals("")
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
            prompt = "Select unit"
        }
    }
}
