abstract class DayOfCode(private val filename: String?) {
    protected fun openStream(filename: String?) = this::class.java.getResourceAsStream("/$filename")?.bufferedReader() ?: throw UnsupportedOperationException()
    
    fun solve() {
        println("Part one: ${solveOne()}")
        println("Part two: ${solveTwo()}")
    }
    abstract fun solveOne(filename: String? = this.filename): Any
    abstract fun solveTwo(filename: String? = this.filename): Any
}
