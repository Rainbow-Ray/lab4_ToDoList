package ru.vika.lab4todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class viewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)
//
        val fileMethod = fileMethods()
        val taskNameText = findViewById<TextView>(R.id.viewNametextView)
        val taskDescText = findViewById<TextView>(R.id.viewDesctextView)
        val doTaskbutton = findViewById<Button>(R.id.doTaskbutton)

        findViewById<Button>(R.id.backToMenubutton2).setOnClickListener{
            toMainMenu()
        }

        var id : Int = 0
        val extras : Bundle? = intent.extras
        if (extras != null){
            id = extras.getInt("id")
        }

        val file = fileMethod.openFile("tasks.json", this)
        var list = fileMethod.fileToList(file).toMutableList()
        var task = list.find { task -> task.id == id }

        if (task != null) {
            val index = list.indexOf(task)
            taskNameText.text = task.name
            taskDescText.text = task.description
            doTaskbutton.setOnClickListener {
                task.done = true
                list[index] = task
                fileMethod.saveListToFile(file, list)
                val alertDone = AlertDialog.Builder(this).setPositiveButton("Ок",
                    {d, id->
                        d.cancel()
                        toMainMenu()})
                alertDone.setMessage("Поздравляем! Задача выполнена").create()
                alertDone.show()
            }

            if(task.done == true){
                doTaskbutton.setOnClickListener(null)
                doTaskbutton.setBackgroundColor(getColor(R.color.white))
                doTaskbutton.setTextColor(getColor(R.color.saturated_green))
                doTaskbutton.text = "Выполнено!"
            }
        }
    }

    fun toMainMenu(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}