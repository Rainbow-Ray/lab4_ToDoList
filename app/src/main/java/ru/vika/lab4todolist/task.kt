package ru.vika.lab4todolist

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class task (
    var id : Int,
    var name: String,
    var description: String
){
    fun toJson(): String{
        return Json.encodeToString(this)
    }
}


