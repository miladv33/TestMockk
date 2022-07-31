package com.example.testmockk.car.piecemaker

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Body
import com.example.testmockk.car.piecemodels.Windows

class BodyMaker() : PieceMaker<Body>() {
    override fun produceNewOne() = Body()
}