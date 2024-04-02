package ru.vika.lab4todolist

import android.content.Context
import android.icu.util.Calendar
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date


class GoodMethods {

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

    fun removeTaskAndSaveToFile(file: File, list: MutableList<task>, task:task){
        val index = list.indexOf(task)
        list.removeAt(index)
        file.writeText(Json.encodeToString(list))
    }

    fun calToDate(date: String) : Array<Int>{
        return arrayOf(date.substring(6).toInt(), date.substring(3,5).toInt()-1,date.substring(0,2).toInt())
    }

    fun isEarlier(newDate: Date): Boolean{
        val oldDate = Calendar.getInstance()
        oldDate.add(Calendar.DATE, -1)
        return newDate.before(oldDate.time)
    }

    fun getTaskById(file: File, id: Int): task?{
        var list = fileToList(file).toMutableList()
        var task = list.find { task -> task.id == id }
        return task
    }

}