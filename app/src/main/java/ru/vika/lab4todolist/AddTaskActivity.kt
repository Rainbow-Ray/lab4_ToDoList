package ru.vika.lab4todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_no_scrolllayout)

        val fileMethod = fileMethods()
        val nameTextEdit = findViewById<EditText>(R.id.nameInputeditText)
        val descTextEdit = findViewById<EditText>(R.id.descInputeditTextTextMultiLine)

        nameTextEdit.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus == false){
                if(nameTextEdit.text.length < 1){
                    nameTextEdit.hint = "Введите название дела"
                    nameTextEdit.setHintTextColor(getColor(R.color.bright_pink))
                }

            }

        }
        
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
                fileMethod.AddTaskAndSaveToFile(file,newTask)
                toMainMenu()
            }
        }
    }
    fun toMainMenu(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}