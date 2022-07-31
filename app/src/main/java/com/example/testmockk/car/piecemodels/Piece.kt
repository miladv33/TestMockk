package com.example.testmockk.car.piecemodels

sealed class Piece(
    var used: Boolean = false,
    var module: Int = -1
)
