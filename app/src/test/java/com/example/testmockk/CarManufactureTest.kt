package com.example.testmockk

import com.example.testmockk.car.CarManufacture
import com.example.testmockk.car.piecemaker.*
import com.example.testmockk.car.piecemodels.Door
import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.google.common.truth.Truth.assertThat
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ManufactureTest {

    @Nested
    inner class PieceMakerTest {
        val doorMaker = spyk<DoorMaker>() as PieceMaker<Door>

        @Test
        fun `add new piece`() {
            doorMaker.createNewPiece()
            assertThat(doorMaker.getListOfPieces()).isNotEmpty()
        }

        @Test
        fun `set door module`() {
            doorMaker.createNewPiece()
            doorMaker.createNewPiece()
            assertThat(doorMaker.getListOfPieces().first().module).isEqualTo(0)
            assertThat(doorMaker.getListOfPieces()[1].module).isEqualTo(1)
        }
    }

    @Nested
    inner class CarManufactureTest {
        private val bodyMaker = spyk<BodyMaker>()
        private val doorMaker = spyk<DoorMaker>()
        private val tierMaker = spyk<TierMaker>()
        private val windowsMaker = spyk<WindowsMaker>()
        private val sportRingMaker = spyk<SportRingMaker>()
        private val listOfPieceMaker = arrayListOf<PieceMaker<*>>(bodyMaker, doorMaker, tierMaker, windowsMaker)
        private val specialListOfPieceMaker = arrayListOf<PieceMaker<*>>(bodyMaker, doorMaker, tierMaker, windowsMaker, sportRingMaker)
        private val carManufacture = spyk(CarManufacture(listOfPieceMaker))
        private val specialCarManufacture = spyk(CarManufacture(specialListOfPieceMaker))

        @Test
        fun `create new pieces `() {
            carManufacture.produceNewPieces()
            verify {
                bodyMaker.createNewPiece()
                doorMaker.createNewPiece()
                tierMaker.createNewPiece()
                windowsMaker.createNewPiece()
            }
        }

        @Test
        fun `create new car`() {
            carManufacture.newCar()
            verify { carManufacture.produceNewPieces() }
            verify { carManufacture.manufactureNewCar() }
            assertThat(carManufacture.carList).isNotEmpty()
        }

        @Test
        fun `car has all the pieces`() {
            carManufacture.newCar()
            assertThat(carManufacture.carList.first().list.first().module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[1].module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[2].module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[3].module).isEqualTo(0)
        }

        @Test
        fun `make empty the piece of list ofter generate one car`() {
            carManufacture.newCar()
            verify { carManufacture.emptyPiecesList() }
            assertThat(carManufacture.piecesList).isEmpty()
        }

        @Test
        fun `upgrade new module for every pieces`() {
            bodyMaker.createNewPiece()
            assertThat(bodyMaker.getModel()).isEqualTo(0)
            bodyMaker.createNewPiece()
            assertThat(bodyMaker.getModel()).isEqualTo(1)
        }

        @Test
        fun `clear last module after fetch`() {
            bodyMaker.createNewPiece()
            bodyMaker.fetchPiece()
            assertThat(bodyMaker.getListOfPieces()).isEmpty()
            assertThat(bodyMaker.getModel()).isEqualTo(0)
            bodyMaker.createNewPiece()
            assertThat(bodyMaker.getModel()).isEqualTo(1)
        }

        @Test
        fun `generate next generation`() {
            carManufacture.newCar()
            assertThat(carManufacture.carList.first().list.first().module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[1].module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[2].module).isEqualTo(0)
            assertThat(carManufacture.carList.first().list[3].module).isEqualTo(0)

            carManufacture.newCar()
            assertThat(carManufacture.carList[1].list.first().module).isEqualTo(1)
            assertThat(carManufacture.carList[1].list[1].module).isEqualTo(1)
            assertThat(carManufacture.carList[1].list[2].module).isEqualTo(1)
            assertThat(carManufacture.carList[1].list[3].module).isEqualTo(1)
        }

        @Test
        fun `create 9 generations of cars`() {
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            carManufacture.newCar()
            assertThat(carManufacture.carList.last().list.first().module).isEqualTo(9)
        }

        @Test
        fun `create cars with different features`() {
            carManufacture.newCar()
            specialCarManufacture.newCar()
            assertThat(carManufacture.carList.first().list).hasSize(4)
            assertThat(specialCarManufacture.carList.first().list).hasSize(5)
        }
    }

}