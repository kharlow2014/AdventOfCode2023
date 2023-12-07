import day.Day07
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Tests {
    
    @Test
    fun `day 7 part one returns 6440`() {
        val expected = 6440L
        
        val actual = Day07().solveOne()
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `day 7 part 2 returns 5905`() {
        val expected = 5905L
        
        val actual = Day07().solveTwo()
        
        assertEquals(expected, actual)
    }
}
