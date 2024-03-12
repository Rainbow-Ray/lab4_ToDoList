package ru.vika.lab4todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = "tasks.json"
        val listOfTasks = fillTasks(fileName)


        val recyclerView : RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecycleAdapter(listOfTasks)

        val addTaskButton = findViewById<ImageButton>(R.id.addTaskbutton)
        addTaskButton.setOnClickListener {
            val intent : Intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

    }

    fun fillTasks(fileName: String): List<task>{
        val file = openFile(fileName)
        val list = fileToList(file)
        return list
    }

    fun openFile(fileName : String) : File{
        val file = File(this.filesDir, fileName)
        if(!file.exists()){
            file.createNewFile()
        }
        return file
    }

    fun fileToList(file : File) : List<task>{
        val text = file.readText()
        val json : List<task>
        if (text.length > 1){
            json = Json.decodeFromString<List<task>>(text)
        }
        else{
            json = listOf()
        }
        return json
    }

    fun clearFile(file : File) : File{
        file.writeText("")
        return file
    }

    fun saveToFile(task: task){
        val file = File(this.filesDir, "tasks.json")
        if(!file.exists()){
            file.createNewFile()
        }
        file.appendText(task.toJson())
    }
}