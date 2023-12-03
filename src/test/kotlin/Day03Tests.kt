import day.Day03
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Tests {
    
    @Test
    fun `day three part one returns 4361`() {
        val expected = 4361
        
        val actual = Day03("03.data").solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day three part two returns 467835`() {
        val expected = 467835
        
        val actual = Day03("03.data").solveTwo()
        
        assertEquals(expected, actual)
    }
}
