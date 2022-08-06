package com.example.testmockk.queue

class RoomManager {
    val rooms = mutableListOf<Room>()

    fun getAllFreeRooms(): List<Room> {
        return rooms.filter { it.isFreeRoom }
    }

    fun thereIsAFreeRoom(): Boolean {
        return getAllFreeRooms().isNotEmpty()
    }

}
