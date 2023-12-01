abstract class DayOfCode {
    fun solve() {
        println("Part one: ${solveOne()}")
        println("Part two: ${solveTwo()}")
    }
    protected abstract fun solveOne(): Any
    protected abstract fun solveTwo(): Any
}
