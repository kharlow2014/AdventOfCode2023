import day.Day06
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Tests {
    
    @Test
    fun `day six part one returns 288`() {
        val expected = 288L
        
        val actual = Day06().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day six part two returns 71503`() {
        val expected = 71503L
        
        val actual = Day06().solveTwo()
        
        assertEquals(expected, actual)
    }
}
