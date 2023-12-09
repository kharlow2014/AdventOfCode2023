import day.Day07
import day.Day08
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Tests {
    
    @Test
    fun `day eight part one returns 6`() {
        val expected = 6
        
        val actual = Day08("08_1.data").solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day eight part two returns 6`() {
        val expected = 6L
        
        val actual = Day08("08_2.data").solveTwo()
        
        assertEquals(expected, actual)
    }
}
