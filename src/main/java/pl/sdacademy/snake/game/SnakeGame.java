package pl.sdacademy.snake.game;

import java.util.Random;


public abstract class SnakeGame {
    private Snake snake;
    private Direction direction;
    private int xBound;
    private int yBound;
    private Point apple;


    public SnakeGame(int xBound, int yBound) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = new Snake(new Point(0, 0));
        this.direction = Direction.DOWN;
        randomizeApple();
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public Snake getSnake() {
        return snake;
    }


    public Point getApple() {
        return apple;
    }


    public void randomizeApple() {
        Random random = new Random();
        do {
            apple = new Point(random.nextInt(xBound), random.nextInt(yBound));
        } while (snake.contains(apple));
    }


    public void start() {
        while (!hasSnakeCrashed()) {
            onNextStep();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean appleEaten = snake.getHead().move(direction).equals(apple);
            snake.move(direction, !appleEaten);
            if (appleEaten) {
                randomizeApple();
            }
        }
        onGameEnded();
    }


    private boolean hasSnakeCrashed() {
        Point head = snake.getHead();
        return isPointOutsideOfBounds(head)
                || snake.getBody().contains(head);
    }


    private boolean isPointOutsideOfBounds(Point point) {
        return point.getX() < 0 || point.getY() < 0
                || point.getX() >= xBound || point.getY() >= yBound;
    }


    public abstract void onGameEnded();


    public abstract void onNextStep();
}
