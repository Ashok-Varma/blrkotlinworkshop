package com.vinaysshenoy.blrkotlinworkshop

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SampleSpec : Spek({

    describe("test setup") {

        describe("a function that succeeds") {
            val func = { 2 + 2 }

            on("executing the function") {

                it("should succeed without failing") {
                    func.shouldNotThrow(Throwable::class)
                }

                it("should return a result") {
                    func() shouldEqualTo 4
                }
            }
        }

        describe("a function that fails") {
            val func = { throw RuntimeException() }

            on("executing the function") {

                it("should throw the exception") {
                    func shouldThrow RuntimeException::class
                }
            }
        }

        describe("produce a matrix") {
            val size = 2
            val generatedMatrix = GOLBoard(size).cells

            on("generation of martix") {
                it("size of list should be square of size") {
                    generatedMatrix.size shouldEqualTo (size * size)
                }

                it("No duplication in the generated matrix") {
                    val cellPositions = mutableListOf(-1)
                    generatedMatrix.forEach {
                        val position = it.x * size + it.y
                        cellPositions.contains(position) shouldEqualTo false
                        cellPositions.add(position)
                    }
                }
            }
        }

        describe("finding neighbour cells") {
            val size = 1
            val board = GOLBoard(size)

            on("Board with size 1") {
                it("will always return null") {
                    val neighbourCells = board.getNumberOfNeighbourCells(board.cells[0])
                    neighbourCells shouldEqual null
                }
            }
        }

        describe("finding neighbour cells") {
            val size = 8
            val board = GOLBoard(size)

            on("Board with size 8") {
                it("size of list should be square of size") {
                    val neighbourCells = board.getNumberOfNeighbourCells(board.cells[0])
                    neighbourCells!!.size shouldEqualTo 3
                }
            }
        }
    }
})