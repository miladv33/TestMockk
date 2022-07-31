package com.example.testmockk.car.piecemaker

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Door

class DoorMaker : PieceMaker<Door>() {
    override fun produceNewOne() = Door()
}