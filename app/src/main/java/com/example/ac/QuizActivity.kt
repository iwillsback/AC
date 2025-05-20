package com.example.ac

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val nameInput: EditText = findViewById(R.id.editTextName)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val checkBox1: CheckBox = findViewById(R.id.checkBox1)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val submitButton: Button = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val name = nameInput.text.toString()
            val selectedId = radioGroup.checkedRadioButtonId
            val selectedRadio = findViewById<RadioButton>(selectedId)
            val trait = selectedRadio.text.toString()

            val selectedTraits = mutableListOf<String>()
            if (checkBox1.isChecked) selectedTraits.add(checkBox1.text.toString())
            if (checkBox2.isChecked) selectedTraits.add(checkBox2.text.toString())
            if (checkBox3.isChecked) selectedTraits.add(checkBox3.text.toString())

            val resultIntent = Intent(this, ResultActivity::class.java)
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("trait", trait)
            resultIntent.putStringArrayListExtra("traits", ArrayList(selectedTraits))
            startActivity(resultIntent)
        }
    }
}