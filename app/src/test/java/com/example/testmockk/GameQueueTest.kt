package com.example.testmockk

import com.example.testmockk.queue.*
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class GameQueueTest {

    val roomManager = spyk(RoomManager())
    val queueManager = spyk(QueueManager())
    val gameQueue = spyk(GameQueue(roomManager, queueManager))
    val person = spyk(Person())

    @Nested
    inner class JoinTOQueue {

        @Test
        fun `enter to game`() {
            val freeRoom = Room()
            val fillRoom = Room(isFreeRoom = false)
            roomManager.rooms.addAll(mutableListOf(freeRoom, fillRoom))
            gameQueue.addNewPersonToQueue(person)
            verify { gameQueue.enterToGame(person) }
            assertThat(person.isPlaying).isTrue()
        }
    }

    @Nested
    inner class ManageRooms {

        @Test
        fun `get all free Rooms`() {
            val freeRoom = Room()
            val fillRoom = Room(isFreeRoom = false)
            roomManager.rooms.addAll(mutableListOf(freeRoom, fillRoom))
            val allFreeRooms = roomManager.getAllFreeRooms()
            assertThat(allFreeRooms.first().isFreeRoom).isTrue()
            assertThat(allFreeRooms).doesNotContain(fillRoom)
        }

        @Test
        fun `if there is a free room go to game`() {
            val freeRoom = Room()
            roomManager.rooms.addAll(mutableListOf(freeRoom))
            gameQueue.enterToGame(person)
            verifyOrder {
                roomManager.thereIsAFreeRoom()
                roomManager.getAllFreeRooms()

            }
            assertThat(person.isPlaying).isTrue()
        }

        @Test
        fun `if there is no free room add to queue`() {
            val filledRoom = Room(isFreeRoom = false)
            roomManager.rooms.addAll(mutableListOf(filledRoom))
            gameQueue.enterToGame(person)

            verifyOrder {
                roomManager.thereIsAFreeRoom()
                roomManager.getAllFreeRooms()
                queueManager.addPersonToQueue(person)
            }
        }

        @Test
        fun `fill room when user joined it`() {
            val freeRoom = Room()
            val freeRoom1 = Room()
            roomManager.rooms.addAll(mutableListOf(freeRoom))
            gameQueue.enterToGame(person)
            assertThat(freeRoom.isFreeRoom).isFalse()
            assertThat(freeRoom1.isFreeRoom).isTrue()
        }
    }
}