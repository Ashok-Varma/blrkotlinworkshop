package com.vinaysshenoy.blrkotlinworkshop.gameoflife

import com.vinaysshenoy.blrkotlinworkshop.GOLBoard

class GameOfLifeControllerImpl(size: Int,seed: Set<Pair<Int, Int>>) : GameOfLifeController<GameOfLifeState>() {

    private var golBoard = GOLBoard(size, seed)

    override fun step(): GameOfLifeState {
        golBoard.moveToNextGeneration()
        return GameOfLifeState(golBoard.cells)
    }

    override fun adaptToViewState(gameState: GameOfLifeState): List<Cell> {
        return gameState.cells
    }

    override fun updateSeed(size: Int,randomSeed: Set<Pair<Int, Int>>) {
        golBoard = GOLBoard(size, randomSeed)
    }

}
