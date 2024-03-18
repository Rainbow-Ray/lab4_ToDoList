package ru.vika.lab4todolist

import android.content.Context
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File



class fileMethods {
    fun openFile(fileName : String, context : Context) : File {
        val file = File(context.filesDir, fileName)
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

    fun AddTaskAndSaveToFile(file: File, task: task){
        var listOfTask = fileToList(file)
        listOfTask= listOfTask.plus(task)
        file.writeText(Json.encodeToString(listOfTask))
    }
    fun saveListToFile(file: File, list: List<task>){
        file.writeText(Json.encodeToString(list))
    }
}