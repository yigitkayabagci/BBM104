import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The Game class is the main class of the application.
 * It sets up the game environment and controls the game loop.
 */
public class Main extends Application {
    private Miner miner; // The miner object
    private Canvas canvas; // The canvas on which the game is drawn
    private GraphicsContext gc; // The graphics context for drawing on the canvas

    private static boolean GameOver = false; // Flag to check if the game is over

    public static int totalWeight = 0; // The total weight of the collected blocks

    public static int collectedMoney = 0; // The total money collected

    public static double totalFuel = 10000; // The total fuel available

    /**
     * Sets the game over status.
     * @param gameOver The game over status to set
     */
    public static void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    /**
     * The start method is the main entry point for any JavaFX application.
     * It is called after the init() method has returned, and after the system is ready for the application to begin running.
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage){
        Pane root = new Pane(); // The root pane
        GameEnvironment gameEnvironment = new GameEnvironment(root); // The game environment
        miner = new Miner(gameEnvironment); // The miner

        // Create a Canvas and get its GraphicsContext
        canvas = new Canvas(GameEnvironment.WIDTH * GameEnvironment.TILE_SIZE, GameEnvironment.HEIGHT * GameEnvironment.TILE_SIZE);
        gc = canvas.getGraphicsContext2D();

        // Add the Canvas to the root Pane
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, GameEnvironment.WIDTH * GameEnvironment.TILE_SIZE, GameEnvironment.HEIGHT * GameEnvironment.TILE_SIZE);

        // Set the key press event handlers
        scene.setOnKeyPressed(event -> {
            if (!GameOver && totalFuel > 0) {
                if (event.getCode() == KeyCode.UP) {
                    miner.flyUp();
                    miner.setAccelerating(true);  // Set isAccelerating to true when UP key is pressed
                } else if (event.getCode() == KeyCode.DOWN) {
                    miner.digDown();
                } else if (event.getCode() == KeyCode.LEFT) {
                    miner.moveLeft();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    miner.moveRight();
                }
            }
        });

        // Set the key release event handlers
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP) {
                miner.setAccelerating(false); // Set isAccelerating to false when UP key is released
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        // Start the game loop
        new AnimationTimer() {
            private long lastUpdate = 0 ;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 60_000_000) { // 60_000_000 nanoseconds = 60 milliseconds
                    Main.totalFuel -= 0.0166667;
                    lastUpdate = now ;
                }

                miner.checkFlying();
                if (miner.isFlying() && !(miner.isAccelerating())){
                    miner.applyGravity();
                }
                render();
            }
        }.start();
    }

    /**
     * The render method is responsible for drawing the game on the canvas.
     */
    private void render() {
        // Clear the Canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Check if the game is over
        if (GameOver) {
            // Draw the game over screen
            gc.setFill(Color.RED);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(50));
            gc.fillText("Game Over", canvas.getWidth() / 3, canvas.getHeight() / 2);
        }
        else if (totalFuel <= 0){
            // Draw the game over screen with the total money collected
            gc.setFill(Color.GREEN);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(50));
            gc.fillText("GAME OVER", canvas.getWidth() / 3, canvas.getHeight() / 2);
            gc.fillText("Collected Money: " + collectedMoney, canvas.getWidth() / 5, canvas.getHeight() / 2 + 50);
        }
        else {
            // Draw the miner's image on the Canvas
            Image minerImage = miner.getImage();
            if (minerImage != null) {
                int offsetX = miner.getOffsetX();
                int offsetY = miner.getOffsetY();

                gc.drawImage(minerImage, miner.getX() * GameEnvironment.TILE_SIZE + offsetX, miner.getY() * GameEnvironment.TILE_SIZE + offsetY);

                // Draw the game stats on the top left of the canvas
                gc.setFill(Color.WHITE);
                gc.setFont(javafx.scene.text.Font.font(20));
                gc.fillText("fuel: " + String.format("%.3f", Main.totalFuel), 10, 30);
                gc.fillText("haul: " + Main.totalWeight, 10, 60);
                gc.fillText("money: " + Main.collectedMoney, 10, 90);
            }
        }
    }

    /**
     * The main method is the entry point for all Java applications.
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}