package extremely.engine

interface Logic {
    fun init()

    fun input()

    fun update()

    fun render()

    fun cleanup()
}

interface LogicFactory {
    fun create(engine: Engine): Logic
}