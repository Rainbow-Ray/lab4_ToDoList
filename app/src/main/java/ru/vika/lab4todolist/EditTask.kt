package ru.vika.lab4todolist

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        val fileMethod = GoodMethods()
        val editName = findViewById<EditText>(R.id.editNametextView)
        val editDesc = findViewById<EditText>(R.id.viewDesctextView)
        val editDateButton = findViewById<Button>(R.id.editDateTextView)
        val dateSpinnerPick = DatePickerDialog(this, R.style.MySpinnerDatePickerStyle)
        val saveChanges = findViewById<Button>(R.id.saveChangesbutton)
        val deleteButton = findViewById<Button>(R.id.deleteTaskbutton2)

        var id = 0
        val extras : Bundle? = intent.extras
        if (extras != null){
            id = extras.getInt("id")
        }

        val file = fileMethod.openFile("tasks.json", this)
        var list = fileMethod.fileToList(file).toMutableList()
        var task = list.find { task -> task.id == id }


        dateSpinnerPick.setOnDateSetListener { view, year, month, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            val newDataTime = newDate.time
            editDateButton.text = DateFormat.format("dd.MM.yyyy", newDataTime)

            if(fileMethod.isEarlier(newDataTime)){
                editDateButton.setTextColor(getColor(R.color.bright_pink))
            }
            else{
                editDateButton.setTextColor(getColor(R.color.black))
            }
        }


        if (task != null) {
            val index = list.indexOf(task)
            editName.setText(task.name)
            editDesc.setText(task.description)

            val taskOldDate = Calendar.getInstance()
            val dateArr = fileMethod.calToDate(task.date)
            taskOldDate.clear()
            taskOldDate.set(dateArr[0], dateArr[1],dateArr[2])
            editDateButton.text = DateFormat.format("dd.MM.yyyy", taskOldDate.time)
            dateSpinnerPick.updateDate(dateArr[0], dateArr[1], dateArr[2])

            if(fileMethod.isEarlier(taskOldDate.time)){
                editDateButton.setTextColor(getColor(R.color.bright_pink))
            }
            else{
                editDateButton.setTextColor(getColor(R.color.black))
            }

            editDateButton.setOnClickListener {
                dateSpinnerPick.create()
                dateSpinnerPick.show()
            }

            editName.setOnFocusChangeListener { v, hasFocus ->
                if(hasFocus == false){
                    if(editName.text.length < 1){
                        editName.hint = "Введите название дела"
                        editName.setHintTextColor(getColor(R.color.bright_pink))
                    }
                }
            }
            
            saveChanges.setOnClickListener{
                if(editName.text.length < 1){
                    Toast.makeText(this, "Поле названия дела пустое. Введите название", Toast.LENGTH_LONG).show()
                }
                else{
                    list[index].date = editDateButton.text.toString()
                    list[index].name = editName.text.toString()
                    list[index].description = editDesc.text.toString()
                    fileMethod.saveListToFile(file, list)
                    roViewActivity(task)
                }
            }

            deleteButton.setOnClickListener{
                fileMethod.removeTaskAndSaveToFile(file, list, task)
                toMainMenu()
            }

            findViewById<Button>(R.id.backToMenubutton3).setOnClickListener {
                roViewActivity(task)
            }
        }
    }
    fun roViewActivity(task : task){
        val intent = Intent(this, viewTaskActivity::class.java)
        intent.putExtra("id", task.id)
        startActivity(intent)
    }

    fun toMainMenu(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}