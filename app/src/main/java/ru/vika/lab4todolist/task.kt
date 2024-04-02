package ru.vika.lab4todolist

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Serializable
data class task (
    var id : Int,
    var name: String,
    var description: String,
    var date: String,
    var done: Boolean
){
    fun toJson(): String{
        return Json.encodeToString(this)
    }
}


