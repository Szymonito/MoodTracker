package com.example.moodtracker.data

object FakeMoodRepository {
    val moodList = mutableListOf<MoodEntry>()

    fun addMood(entry: MoodEntry){
        moodList.add(0, entry)
    }

    fun removeMood(entry: MoodEntry){
        moodList.remove(entry)
    }
}
