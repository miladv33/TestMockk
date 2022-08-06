package com.example.testmockk.queue

class QueueManager {
    private val persons = mutableListOf<Person>()
    fun addPersonToQueue(person: Person) {
        persons.add(person)
    }
}