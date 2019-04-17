package pl.sdacademy.snake;

import org.junit.Before;
import org.junit.Test;
import pl.sdacademy.snake.game.Direction;
import pl.sdacademy.snake.game.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointTest {
    private Point point4_4;

    @Before
    public void setUp() {
        this.point4_4 = new Point(4, 4);
    }

    @Test
    public void testMoveUp() {
        // given
        // when
        Point result = point4_4.move(Direction.UP);
        // then
        assertEquals(new Point(4, 3), result);
    }

    @Test
    public void testMoveDown() {
        // given
        // when
        Point result = point4_4.move(Direction.DOWN);
        // then
        assertEquals(new Point(4, 5), result);
    }

    @Test
    public void testMoveLeft() {
        // given
        // when
        Point result = point4_4.move(Direction.LEFT);
        // then
        assertEquals(new Point(3, 4), result);
    }

    @Test
    public void testMoveRight() {
        // given
        // when
        Point result = point4_4.move(Direction.RIGHT);
        // then
        assertEquals(new Point(5, 4), result);
    }

    @Test
    public void testEqualsForEqualPoint() {
        // given
        Point equalPoint = new Point(4, 4);
        // when
        boolean result = point4_4.equals(equalPoint);
        // then
        assertTrue(result);
    }

    @Test
    public void testEqualsForSamePoint() {
        // given
        // when
        boolean result = point4_4.equals(point4_4);
        // then
        assertTrue(result);
    }

    @Test
    public void testEqualsForDifferentX() {
        // given
        Point otherPoint = new Point(10, 4);
        // when
        boolean result = point4_4.equals(otherPoint);
        // then
        assertFalse(result);
    }
}
