import day.Day02
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Tests {
    
    @Test
    fun `day two part one returns 8`() {
        val expected = 8
        
        val actual = Day02().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day two part two returns 2286`() {
        val expected = 2286L
        
        val actual = Day02().solveTwo()
        
        assertEquals(expected, actual)
    }
}
