import day.Day09
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Tests {
    
    @Test
    fun `day nine part one returns 114`() {
        val expected = 114L
        
        val actual = Day09().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day nine part two returns `() {
        val expected = 2L
        
        val actual = Day09().solveTwo()
        
        assertEquals(expected, actual)
    }
}
