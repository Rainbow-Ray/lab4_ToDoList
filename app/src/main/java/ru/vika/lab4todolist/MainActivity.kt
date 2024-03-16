package ru.vika.lab4todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.encodeToString
import java.io.File
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileMethod = fileMethods()
        val fileName = "tasks.json"
        val listOfTasks = fillTasks(fileName, fileMethod)

        val recyclerView : RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecycleAdapter(listOfTasks,
            ({task ->
        val intent : Intent = Intent(this, viewTaskActivity::class.java)
        intent.putExtra("id", task.id)
        startActivity(intent)})
        )

//        val onCardClick ={ task ->
//            val intent : Intent = Intent(this, viewTaskActivity::class.java)
//            intent.putExtra("id", task.id)
//            startActivity(intent)}




//        val adapter = MyRecycleAdapter(listOfTasks)
//        adapter.onItemClick = {
//            task ->
//            val intent : Intent = Intent(this, viewTaskActivity::class.java)
//            intent.putExtra("id", task.id)
//            startActivity(intent)
//        }

        val addTaskButton = findViewById<ImageButton>(R.id.addTaskbutton)
        addTaskButton.setOnClickListener {
            val intent : Intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

    }

    fun fillTasks(fileName: String, fileMethod: fileMethods): List<task>{
        val file = fileMethod.openFile(fileName, this)
        val list = fileMethod.fileToList(file)
        return list
    }



}