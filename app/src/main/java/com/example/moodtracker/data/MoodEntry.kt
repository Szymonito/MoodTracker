package com.example.moodtracker.data

data class MoodEntry (
     val description: String,
    val feeling: String,
    val type: String,
    val statements: MutableList<String>,
    val rating: Float,
    val importance: Boolean
 )

