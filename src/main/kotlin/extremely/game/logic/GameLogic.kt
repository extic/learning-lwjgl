package extremely.game.logic

import extremely.engine.Engine
import extremely.engine.Logic
import extremely.engine.LogicFactory


class GameLogicFactory : LogicFactory {
    override fun create(engine: Engine): Logic =
        GameLogic(engine)
}

class GameLogic(private val engine: Engine) : Logic {
    override fun init() {

    }

    override fun input() {

    }

    override fun update() {

    }

    override fun render() {

    }

    override fun cleanup() {

    }
}

