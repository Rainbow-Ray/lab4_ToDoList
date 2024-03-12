package ru.vika.lab4todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val nameTextEdit = findViewById<EditText>(R.id.nameInputeditText)
        val descTextEdit = findViewById<EditText>(R.id.descInputeditTextTextMultiLine)

        findViewById<Button>(R.id.backToMenubutton).setOnClickListener {
            val intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.saveTaskbutton).setOnClickListener{
            if(nameTextEdit.text.length < 1){
                Toast.makeText(this, "Поле названия дела пустое. Введите название", Toast.LENGTH_SHORT).show()
            }
        }
    }
}