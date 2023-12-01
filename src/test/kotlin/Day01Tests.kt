import day.Day01
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Tests {
    @Test
    fun `day one part one returns 142`() {
        val expected = 142
        
        val actual = Day01("01_1.data").solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day one part two returns 281`() {
        val expected = 281
        
        val actual = Day01("01_2.data").solveTwo()
        
        assertEquals(expected, actual)
    }
}
