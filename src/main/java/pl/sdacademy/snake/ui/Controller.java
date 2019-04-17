package pl.sdacademy.snake.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.sdacademy.snake.game.Direction;
import pl.sdacademy.snake.game.Point;
import pl.sdacademy.snake.game.Snake;
import pl.sdacademy.snake.game.SnakeGame;

public class Controller {
    private final static int POINT_SIZE = 20;
    @FXML
    private Canvas canvas;
    @FXML
    private Label gameEndedLabel;
    @FXML
    private GridPane buttonsGridPane;
    private SnakeGame snakeGame;
    private GraphicsContext graphicsContext;

    public void initialize() {
        gameEndedLabel.setManaged(false);
        graphicsContext = canvas.getGraphicsContext2D();
        snakeGame = new SnakeGame((int) canvas.getWidth() / POINT_SIZE,
                (int) canvas.getHeight() / POINT_SIZE) {
            @Override
            public void onGameEnded() {
                gameEndedLabel.setManaged(true);
                gameEndedLabel.setVisible(true);
                buttonsGridPane.setManaged(false);
                buttonsGridPane.setVisible(false);

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Koniec gry!");
                    alert.setHeaderText(null);
                    alert.setContentText("Wąż się rozbił, gra zakończona");
                    alert.showAndWait();
                });
            }

            @Override
            public void onNextStep() {
                drawGame();
            }
        };

        Thread thread = new Thread(() -> {
            snakeGame.start();
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void drawGame() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.RED);
        Snake snake = snakeGame.getSnake();
        drawPoint(snake.getHead());
        graphicsContext.setFill(Color.GREEN);
        snake.getBody().forEach(this::drawPoint);
        graphicsContext.setFill(Color.YELLOW);
        drawPoint(snakeGame.getApple());
    }

    private void drawPoint(Point point) {
        graphicsContext.fillRect(point.getX() * POINT_SIZE,
                point.getY() * POINT_SIZE, POINT_SIZE, POINT_SIZE);
    }

    public void onUpButtonClick() {
        snakeGame.setDirection(Direction.UP);
    }

    public void onDownButtonClick() {
        snakeGame.setDirection(Direction.DOWN);
    }

    public void onRightButtonClick() {
        snakeGame.setDirection(Direction.RIGHT);
    }

    public void onLeftButtonClick() {
        snakeGame.setDirection(Direction.LEFT);
    }
}
