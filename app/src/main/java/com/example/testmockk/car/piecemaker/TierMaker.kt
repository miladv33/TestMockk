package com.example.testmockk.car.piecemaker

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Tier

class TierMaker() : PieceMaker<Tier>() {
    override fun produceNewOne() = Tier()
}