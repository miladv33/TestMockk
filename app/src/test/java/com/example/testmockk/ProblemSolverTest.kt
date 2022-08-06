package com.example.testmockk

import com.example.testmockk.problemsolver.Problem
import com.example.testmockk.problemsolver.ProblemSolver
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProblemSolverTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeAll
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)

    }

    @AfterAll
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    val problemSolver = spyk<ProblemSolver>()
    val problem = spyk<Problem>()
    val secondProblem = spyk<Problem>()


    @Test
    fun `solve the problem`() = runBlocking {
        problemSolver.addNewProblem(problem)
        coVerify { problemSolver.solve(any()) }
        assertThat(problem.isSolved).isTrue()
    }

    @Test
    fun `remove the problem from the list after solve it`() = runBlocking {
        problemSolver.addNewProblem(problem)
        delay(1000)
        coVerify { problemSolver.solve(any()) }
        verify { problemSolver.removeSolvedProblemFromList(problem) }
        assertThat(problemSolver.getProblemQueue()).isEmpty()
    }

    @Test
    fun `after solve first problem go to solve another one`() = runBlocking {
        problemSolver.addNewProblem(problem)
        delay(1000)
        coVerify { problemSolver.solveAnotherProblem() }
    }


    @Test
    fun `add second problem to queue instead of solve it and then solve it`() = runBlocking {
        problemSolver.addNewProblem(problem)
        problemSolver.addNewProblem(secondProblem)
        coVerifyOrder {
            problemSolver.solve(any())
            problemSolver.removeSolvedProblemFromList(any())
            problemSolver.solveAnotherProblem()
            problemSolver.getProblemQueue()
        }
        assertThat(problemSolver.getProblemQueue()).isEmpty()
        assertThat(problem.isSolved).isTrue()
        assertThat(secondProblem.isSolved).isTrue()
    }

}