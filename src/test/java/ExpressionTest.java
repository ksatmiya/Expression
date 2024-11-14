import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    void testSimpleAddition() {
        assertEquals(5.0, Expression.evaluate("2 + 3"), 0.0001);
    }

    @Test
    void testSimpleSubtraction() {
        assertEquals(-1.0, Expression.evaluate("2 - 3"), 0.0001);
    }

    @Test
    void testMultiplication() {
        assertEquals(6.0, Expression.evaluate("2 * 3"), 0.0001);
    }

    @Test
    void testDivision() {
        assertEquals(2.0, Expression.evaluate("6 / 3"), 0.0001);
    }


    @Test
    void testDivisionByZero() {
        assertEquals(0.0, Expression.evaluate("4 / 0"), 0.0001); // Деление на ноль возвращает 0
    }

    @Test
    void testEmptyExpression() {
        assertEquals(0.0, Expression.evaluate(""), 0.0001);
    }
}
