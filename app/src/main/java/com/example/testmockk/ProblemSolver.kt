package com.example.testmockk

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProblemSolver {

    private val problemQueueList = ArrayList<Problem>()
    var isSolvingProblem = false
        private set

    suspend fun addNewProblem(problem: Problem) {
        problemQueueList.add(problem)
        if (!isSolvingProblem) {
            isSolvingProblem = true
            solve(problem)
        }
    }

    fun getProblemQueue(): List<Problem> {
        return problemQueueList
    }

    suspend fun solve(problem: Problem) {
        delay(1000)
        isSolvingProblem = false
        problem.isSolved = true
        removeSolvedProblemFromList(problem)
        solveAnotherProblem()
    }

    fun removeSolvedProblemFromList(problem: Problem) {
        problemQueueList.remove(problem)
    }

    suspend fun solveAnotherProblem() {
        if (getProblemQueue().isNotEmpty()) {
            solve(getProblemQueue().first())
        }
    }
}