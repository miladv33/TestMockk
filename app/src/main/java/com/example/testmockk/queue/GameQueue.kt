package com.example.testmockk.queue

class GameQueue(private val roomManager: RoomManager, private val queueManager: QueueManager) {

    fun addNewPersonToQueue(person: Person) {
        enterToGame(person)
    }

    fun enterToGame(person: Person) {
        if (roomManager.thereIsAFreeRoom()) {
            person.isPlaying = true
            roomManager.getAllFreeRooms().first().isFreeRoom = false
        } else {
            queueManager.addPersonToQueue(person)
        }
    }


}