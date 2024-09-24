import javafx.scene.image.ImageView;

/**
 * The GameBlocks class represents the blocks in the game.
 * It is an abstract class that other specific types of blocks will extend.
 */
public abstract class GameBlocks {
    private final int value; // The value of the block
    private final boolean isObstacle; // Flag to check if the block is an obstacle
    private final ImageView imageView; // The image view of the block

    private final boolean isEndGame; // Flag to check if the block ends the game

    private final int weight; // The weight of the block

    /**
     * Constructor for the GameBlocks class.
     * @param value The value of the block
     * @param isObstacle Whether the block is an obstacle
     * @param imageView The image view of the block
     * @param isEndGame Whether the block ends the game
     * @param weight The weight of the block
     */
    public GameBlocks(int value,boolean isObstacle, ImageView imageView, boolean isEndGame,int weight) {
        this.value = value;
        this.isObstacle = isObstacle;
        this.imageView = imageView;
        this.isEndGame = isEndGame;
        this.weight = weight;
    }

    /**
     * Returns the image view of the block.
     * @return The image view of the block
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Checks if the block ends the game.
     * @return True if the block ends the game, false otherwise
     */
    public boolean isEndGame() {
        return isEndGame;
    }

    /**
     * Returns the weight of the block.
     * @return The weight of the block
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns the value of the block.
     * @return The value of the block
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the block is an obstacle.
     * @return True if the block is an obstacle, false otherwise
     */
    public boolean getIsObstacle() {
        return isObstacle;
    }
}