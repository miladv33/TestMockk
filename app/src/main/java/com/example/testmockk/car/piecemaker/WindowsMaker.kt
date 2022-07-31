package com.example.testmockk.car.piecemaker

import com.example.testmockk.car.piecemaker.base.PieceMaker
import com.example.testmockk.car.piecemodels.Windows

class WindowsMaker() : PieceMaker<Windows>() {
        override fun produceNewOne() = Windows()
    }