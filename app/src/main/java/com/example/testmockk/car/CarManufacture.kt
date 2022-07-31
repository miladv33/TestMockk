package com.example.testmockk.car

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Piece

class CarManufacture(private val listOfPieceMaker: ArrayList<PieceMaker<*>>) {

    val carList = ArrayList<Car>()

    var piecesList = ArrayList<Piece>()

    fun newCar() {
        produceNewPieces()
        manufactureNewCar()
        emptyPiecesList()
    }

    fun produceNewPieces() {
        listOfPieceMaker.forEach {
            it.createNewPiece()
            piecesList.add(it.fetchPiece())
        }
    }

    fun manufactureNewCar() {
        val car = Car(ArrayList(piecesList))
        carList.add(car)
    }

    fun emptyPiecesList() {
        piecesList.clear()
    }

}