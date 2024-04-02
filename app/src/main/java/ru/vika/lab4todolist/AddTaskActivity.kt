package ru.vika.lab4todolist

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.icu.util.Calendar

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val methods = GoodMethods()

        val fileMethod = GoodMethods()
        val nameTextEdit = findViewById<EditText>(R.id.nameInputeditText)
        val descTextEdit = findViewById<EditText>(R.id.descInputeditTextTextMultiLine)

        val datePickerButton = findViewById<Button>(R.id.datePickerButton)
        val date = Calendar.getInstance()
        datePickerButton.text = DateFormat.format("dd.MM.yyyy", date.time)
        val dateSpinnerPick : DatePickerDialog = DatePickerDialog(this, R.style.MySpinnerDatePickerStyle)

        dateSpinnerPick.setOnDateSetListener { view, year, month, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            val newDataTime = newDate.time
            datePickerButton.text = DateFormat.format("dd.MM.yyyy", newDataTime)

            if(methods.isEarlier(newDataTime)){
                datePickerButton.setTextColor(getColor(R.color.bright_pink))
            }
            else{
                datePickerButton.setTextColor(getColor(R.color.black))
            }
        }

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

        datePickerButton.setOnClickListener {
            dateSpinnerPick.create()
            dateSpinnerPick.show()
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
                val newTask = task(newId, nameTextEdit.text.toString(), descTextEdit.text.toString(), datePickerButton.text.toString(), false)
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