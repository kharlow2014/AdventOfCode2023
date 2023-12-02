import day.Day02
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Tests {
    
    @Test
    fun `day two part one returns 8`() {
        val expected = 8
        
        val actual = Day02("02.data").solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day two part two returns 2286`() {
        val expected = 2286L
        
        val actual = Day02("02.data").solveTwo()
        
        assertEquals(expected, actual)
    }
}
