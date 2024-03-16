package ru.vika.lab4todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class viewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        val fileMethod = fileMethods()
        val taskNameText = findViewById<TextView>(R.id.viewNametextView)
        val taskDescText = findViewById<TextView>(R.id.viewDesctextView)

        findViewById<Button>(R.id.backToMenubutton2).setOnClickListener{
            toMainMenu()
        }

        var id : Int = 0
        val extras : Bundle? = intent.extras
        if (extras != null){
            id = extras.getInt("id")
        }

        val file = fileMethod.openFile("tasks.json", this)
        var list = fileMethod.fileToList(file)

        var task = list.find { task -> task.id == id }
        taskNameText.text = task?.name
        taskDescText.text = task?.description



    }
    fun toMainMenu(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}