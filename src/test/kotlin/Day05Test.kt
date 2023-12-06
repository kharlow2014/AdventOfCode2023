import org.junit.jupiter.api.Test
import day.Day05
import kotlin.test.assertEquals

class Day05Test {
    
    @Test
    fun `day five part one returns 35`() {
        val expected = 35L
        
        val actual = Day05().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day five part two returns 46`() {
        val expected = 46L
        
        val actual = Day05().solveTwo()
        
        assertEquals(expected, actual)
    }
}
