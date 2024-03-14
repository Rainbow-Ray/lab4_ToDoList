package ru.vika.lab4todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class viewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        val id : Int
        val extras : Bundle? = intent.extras
        if (extras != null){
            id = extras.getInt("id")
        }


    }
}