abstract class DayOfCode(private val filename: String) {
    protected fun openStream() = this::class.java.getResourceAsStream("/$filename")?.bufferedReader() ?: throw UnsupportedOperationException()
    
    fun solve() {
        println("Part one: ${solveOne()}")
        println("Part two: ${solveTwo()}")
    }
    abstract fun solveOne(): Any
    abstract fun solveTwo(): Any
}
