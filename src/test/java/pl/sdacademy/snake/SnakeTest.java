package pl.sdacademy.snake;

import org.junit.Test;
import pl.sdacademy.snake.game.Point;
import pl.sdacademy.snake.game.Snake;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SnakeTest {
    @Test
    public void testContainsForPointOutsideOfSnake() {
        // given
        Snake snake = new Snake(new Point(2, 2),
                Arrays.asList(new Point(3, 2), new Point(3, 3)));
        Point point = new Point(10, 10);
        // when
        boolean result = snake.contains(point);
        // then
        assertFalse(result);
    }

    @Test
    public void testContainsForHeadPoint() {
        // given
        Snake snake = new Snake(new Point(2, 2),
                Arrays.asList(new Point(3, 2), new Point(3, 3)));
        Point point = new Point(2, 2);
        // when
        boolean result = snake.contains(point);
        // then
        assertTrue(result);
    }

    @Test
    public void testContainsForBodyPoint() {
        // given
        Snake snake = new Snake(new Point(2, 2),
                Arrays.asList(new Point(3, 2), new Point(3, 3)));
        Point point = new Point(3, 2);
        // when
        boolean result = snake.contains(point);
        // then
        assertTrue(result);
    }
}
