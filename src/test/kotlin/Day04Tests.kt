import day.Day04
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Tests {
    
    @Test
    fun `day four part one returns 13`() {
        val expected = 13
        
        val actual = Day04().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day four part two returns 30`() {
        val expected = 30
        
        val actual = Day04().solveTwo()
        
        assertEquals(expected, actual)
    }
}
