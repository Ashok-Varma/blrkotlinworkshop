package com.vinaysshenoy.blrkotlinworkshop

import com.vinaysshenoy.blrkotlinworkshop.gameoflife.Cell


/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 15/09/18
 */


private fun randomMatrix(size: Int): List<Cell> {
    return List(size * size) { i -> Cell(i / size, i % size, Math.random() < 0.3) }
}

class GOLBoard(var cells: List<Cell>) {

    constructor (size: Int) : this(randomMatrix(size))

    constructor (size: Int, seed: Set<Pair<Int, Int>>) : this(
            List(size * size) {
                val x = it / size
                val y = it % size
                Cell(x, y, seed.contains(Pair(x, y)))
            })

    private var generation = 1

    fun getCellAtPosition(x: Int, y: Int): Cell? {
        cells.forEach {
            if (it.x == x && it.y == y) {
                return it
            }
        }
        return null
    }


    fun getALiveCells(): Set<Pair<Int, Int>> {
        val aliveCells = mutableSetOf<Pair<Int, Int>>()

        cells.forEach {
            if (it.alive) {
                aliveCells.add(Pair(it.x, it.y))
            }
        }
        return aliveCells
    }

    fun getNeighbourCells(cell: Cell): List<Cell>? {
        if (cells.size <= 1) {
            return null
        }

        val neighbourCells = mutableListOf<Cell>()
        cells.forEach {
            val horizontalDistance = Math.abs(it.x - cell.x)
            val verticalDistance = Math.abs(it.y - cell.y)
            if ((horizontalDistance <= 1 && verticalDistance <= 1)
                    && (horizontalDistance + verticalDistance != 0)) {
                neighbourCells.add(it)
            }
        }
        return neighbourCells
    }

    private fun getNeighbourLiveCellCount(cell: Cell): Int {
        var noOfLiveCells = 0
        getNeighbourCells(cell)?.forEach {
            if (it.alive) {
                noOfLiveCells++
            }
        }
        return noOfLiveCells;
    }

    fun moveToNextGeneration() {
        val nextGenCells = mutableListOf<Cell>()

        cells.forEach { currentCell ->
            val noOfLiveCells = getNeighbourLiveCellCount(currentCell)

            if (currentCell.alive) {
                when {
                    noOfLiveCells < 2 -> nextGenCells.add(currentCell.copy(x = currentCell.x, y = currentCell.y, alive = false))
                    noOfLiveCells < 4 -> nextGenCells.add(currentCell.copy(x = currentCell.x, y = currentCell.y, alive = true))
                    else -> nextGenCells.add(currentCell.copy(x = currentCell.x, y = currentCell.y, alive = false))
                }
            } else {
                when (noOfLiveCells) {
                    3 -> nextGenCells.add(currentCell.copy(x = currentCell.x, y = currentCell.y, alive = true))
                    else -> nextGenCells.add(currentCell.copy(x = currentCell.x, y = currentCell.y, alive = false))
                }
            }
        }

        generation++

        cells = nextGenCells
    }
}

