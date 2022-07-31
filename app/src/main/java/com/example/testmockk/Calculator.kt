package com.example.testmockk

class Calculator {
    fun calculate(i: Int, i1: Int): Int {
        return i + i1
    }

    fun convertToWord(number: Int): String {
        return ""
    }

    fun sumWordy(i: Int, i1: Int): Any {
        return convertToWord(calculate(i, i1))
    }

    fun convertToNumber(any: String):Int{
        return 2
    }

}