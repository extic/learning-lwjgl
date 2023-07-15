package extremely.engine

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback

class Engine(private val settings: Settings, logicFactory: LogicFactory) {

    private val logic: Logic
    private val errorCallback: GLFWErrorCallback
    private lateinit var window: Window

    init {
        logic = logicFactory.create(this)

        errorCallback = GLFWErrorCallback.createPrint(System.err)
        GLFW.glfwSetErrorCallback(errorCallback)
    }

    fun init() {
        window = Window(settings)
        window.init()
    }

    fun stop() {
        errorCallback.free()
    }

    public fun run() {
        var isRunning = true
        var previous = System.nanoTime()
        var lag = 0f
        while (isRunning) {
            val current = System.nanoTime()
            val elapsed = current - previous
            previous = current
            lag += elapsed

            processInput()

            while (lag >= MS_PER_FRAME) {
                update()
                lag -= MS_PER_FRAME
            }

            render()

            if (window.windowShouldClose()) {
                isRunning = false
            }
        }
    }

    private fun processInput() {
        logic.input()
    }

    fun render() {
        logic.render()
        window.update()
    }

    fun update() {
        logic.update()
    }

    fun cleanup() {
        window.cleanup()
        logic.cleanup()
        errorCallback.free()
        GLFW.glfwTerminate()
    }

    companion object {
        const val FRAME_RATE = 100
        const val MS_PER_FRAME = 1000f / FRAME_RATE
    }
}