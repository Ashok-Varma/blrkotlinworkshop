package com.vinaysshenoy.blrkotlinworkshop.gameoflife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinaysshenoy.blrkotlinworkshop.Controller
import com.vinaysshenoy.blrkotlinworkshop.MviView
import com.vinaysshenoy.blrkotlinworkshop.R.layout
import com.vinaysshenoy.blrkotlinworkshop.ViewControllerBinding
import kotlinx.android.synthetic.main.screen_gameoflife.*

class GameOfLifeScreen : Fragment(),
        MviView<GameOfLifeState, GameOfLifeSideEffect> {

    lateinit var controller: Controller<GameOfLifeUserEvent, GameOfLifeState, GameOfLifeSideEffect>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        controller = GameOfLifeControllerImpl(16, randomSeed())
        viewLifecycleOwner.lifecycle.addObserver(ViewControllerBinding(controller, this))
        val view = inflater.inflate(layout.screen_gameoflife, container, false)
        view.setOnClickListener {
            (controller as GameOfLifeControllerImpl).updateSeed(16, randomSeed())
        }

        return view;
    }

    private fun randomSeed(): Set<Pair<Int, Int>> {
        val random = Math.random()
        return when {
            random <= 0.3 -> Oscillators_Blinker
            random <= 0.6 -> Oscillators_Toad
            else -> Spaceships_Glider
        }
    }

    override fun render(state: GameOfLifeState) {
        widget.cells = state.cells
    }

    override fun sideEffect(sideEffect: GameOfLifeSideEffect) {

    }

    val Oscillators_Blinker = setOf(
            2 to 1,
            2 to 2,
            2 to 3
    )

    val Oscillators_Toad = setOf(
            2 to 2,
            2 to 3,
            2 to 4,
            3 to 3,
            3 to 4,
            3 to 5
    )
    val Spaceships_Glider = setOf(
            2 to 2,
            3 to 3,
            3 to 4,
            4 to 3,
            2 to 4
    )
}
