package com.example.testmockk.car.piecemaker.base

import com.example.testmockk.car.piecemodels.Piece

abstract class PieceMaker<T: Piece>:IPieceMaker {
    private val list = ArrayList<T>()
    private var lastModule = -1

    fun  getModel(): Int = lastModule

    override fun createNewPiece(){
        val newOne = produceNewOne()
        setModule(newOne)
        list.add(newOne)
    }
    abstract fun produceNewOne():T

    fun getListOfPieces():List<T> = list

    private fun setModule(piece: T) {
        lastModule++
        piece.module = lastModule
    }

     fun fetchPiece(): T {
         val last = list.last()
         list.remove(last)
         return last
     }

}