package com.example.testmockk.car.piecemaker

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Door
import com.example.testmockk.car.piecemodels.SportRing

class SportRingMaker : PieceMaker<SportRing>() {
    override fun produceNewOne() = SportRing()
}