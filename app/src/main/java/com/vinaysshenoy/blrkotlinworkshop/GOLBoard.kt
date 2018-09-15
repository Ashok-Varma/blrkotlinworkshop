package com.vinaysshenoy.blrkotlinworkshop

import com.vinaysshenoy.blrkotlinworkshop.gameoflife.Cell


/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 15/09/18
 */

class GOLBoard(size: Int) {

    val cells = randomMatrix(size)
    val generation = 1

    private fun randomMatrix(size: Int): List<Cell> {
        return List(size * size) { i -> Cell(i / size, i % size, Math.random() < 0.3) }
    }

    fun getNumberOfNeighbourCells(cell: Cell): List<Cell>? {
        if(cells.size <= 1){
            return null
        }
        cells.forEach{

        }
        return null
    }
}
//fun produceNextGenerationCells(generatedMatrix: List<Cell>): List<Cell> {
//
//}

