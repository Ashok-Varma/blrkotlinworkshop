package com.vinaysshenoy.blrkotlinworkshop

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GOLBoardSpec : Spek({

    describe("A GOL Board") {

        describe("make a matrix of size 2x2") {
            val size = 2
            val generatedMatrix = GOLBoard(size).cells

            on("generation of matrix") {
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
                    val neighbourCells = board.getNeighbourCells(board.cells[0])
                    neighbourCells shouldEqual null
                }
            }

            val size8 = 8
            val board8 = GOLBoard(size8)

            val testInputs = setOf(
                    board8.cells[0] to 3,
                    board8.cells[1] to 5,
                    board8.cells[size8 - 1] to 3,
                    board8.cells[size8] to 5,
                    board8.getCellAtPosition(4, 4) to 8,
                    board8.cells[board8.cells.size - 1] to 3
            )

            testInputs.forEach { (inputCell, neighbourCellCount) ->
                on("Board with size 8 and cell $inputCell") {
                    it("should have $neighbourCellCount neighbours") {
                        val neighbourCells = board8.getNeighbourCells(inputCell!!)
                        neighbourCells!!.size shouldEqualTo neighbourCellCount
                    }
                }
            }
        }

        //Blinker (period 2)
        val blinkerSeedInput = setOf(
                2 to 1,
                2 to 2,
                2 to 3
        )
        val blinkerSeedOutput = setOf(
                1 to 2,
                2 to 2,
                3 to 2
        )

        describe("Game Predefine model test with Blinker model") {
            val golBoard by memoized { GOLBoard(5, blinkerSeedInput) }

            on("first iteration") {
                golBoard.moveToNextGeneration()
                it("input should rotate 90") {
                    golBoard.getALiveCells() shouldEqual blinkerSeedOutput
                }
            }

            on("second iteration") {
                golBoard.moveToNextGeneration()
                golBoard.moveToNextGeneration()
                it("should be same as input") {
                    golBoard.getALiveCells() shouldEqual blinkerSeedInput
                }
            }
        }

        // Bad Test 101

//        describe("game rules") {
//            val size = 8
//            val golBoard = GOLBoard(size)
//
//            val aliveWithLessThanTwoAliveNeighbourCell = mutableListOf<Cell>()
//            val aliveWithTwoOrThreeAliveNeighbourCell = mutableListOf<Cell>()
//            val aliveWithMoreThreeAliveNeighbourCell = mutableListOf<Cell>()
//            val deadWithThreeAliveNeighbourCell = mutableListOf<Cell>()
//            val deadWithNotThreeAliveNeighbourCell = mutableListOf<Cell>()
//
//            golBoard.cells.forEach { it ->
//                val noOfLiveCells = golBoard.getNeighbourLiveCellCount(it)
//
//                if (it.alive) {
//                    when {
//                        noOfLiveCells < 2 -> aliveWithLessThanTwoAliveNeighbourCell.add(it)
//                        noOfLiveCells < 4 -> aliveWithTwoOrThreeAliveNeighbourCell.add(it)
//                        else -> aliveWithMoreThreeAliveNeighbourCell.add(it)
//                    }
//                } else {
//                    when (noOfLiveCells) {
//                        3 -> deadWithThreeAliveNeighbourCell.add(it)
//                        else -> deadWithNotThreeAliveNeighbourCell.add(it)
//                    }
//                }
//
//            }
//
//            golBoard.moveToNextGeneration()
//
//
//            on("Any live cell with fewer than two live neighbors") {
//                it("dies, as if by under population.") {
//                    aliveWithLessThanTwoAliveNeighbourCell.forEach {
//                        golBoard.getCellAtPosition(it.x, it.y)!!.alive shouldEqualTo false
//                    }
//                }
//            }
//
//            on("Any live cell with two or three live neighbors") {
//                it("lives on to the next generation.") {
//                    aliveWithTwoOrThreeAliveNeighbourCell.forEach {
//                        golBoard.getCellAtPosition(it.x, it.y)!!.alive shouldEqualTo true
//                    }
//                }
//            }
//
//            on("Any live cell with more than three live neighbors ") {
//                it("dies, as if by overpopulation.") {
//                    aliveWithMoreThreeAliveNeighbourCell.forEach {
//                        golBoard.getCellAtPosition(it.x, it.y)!!.alive shouldEqualTo false
//                    }
//                }
//            }
//
//            on("Any dead cell with exactly three live neighbors") {
//                it("becomes a live cell, as if by reproduction.") {
//                    deadWithThreeAliveNeighbourCell.forEach {
//                        golBoard.getCellAtPosition(it.x, it.y)!!.alive shouldEqualTo true
//                    }
//                }
//            }
//
//
//            on("Any dead cell with any number of live neighbors other than three") {
//                it("should remain dead") {
//                    deadWithNotThreeAliveNeighbourCell.forEach {
//                        golBoard.getCellAtPosition(it.x, it.y)!!.alive shouldEqualTo false
//                    }
//                }
//            }
//        }
    }
})