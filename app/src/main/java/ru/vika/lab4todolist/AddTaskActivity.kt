package ru.vika.lab4todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val fileMethod = fileMethods()
        val nameTextEdit = findViewById<EditText>(R.id.nameInputeditText)
        val descTextEdit = findViewById<EditText>(R.id.descInputeditTextTextMultiLine)

        findViewById<Button>(R.id.backToMenubutton).setOnClickListener {
            toMainMenu()
        }
        findViewById<Button>(R.id.saveTaskbutton).setOnClickListener{
            if(nameTextEdit.text.length < 1){
                Toast.makeText(this, "Поле названия дела пустое. Введите название", Toast.LENGTH_LONG).show()
            }
            else{
                val file = fileMethod.openFile("tasks.json", this)
                val listOfTasks = fileMethod.fileToList(file)
                val newId: Int
                if (listOfTasks.size > 0){
                    newId = listOfTasks.last().id + 1
                }
                else {
                    newId = 0
                }

                val newTask = task(newId, nameTextEdit.text.toString(), descTextEdit.text.toString(), false)
                fileMethod.saveToFile(file,newTask)
                toMainMenu()
            }
        }
    }
    fun toMainMenu(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}