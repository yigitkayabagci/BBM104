import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * The GoldBlock class represents a gold block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class GoldBlock extends GameBlocks{

    // The image for the gold block
    private static final Image image = new Image("assets\\underground\\valuable_goldium.png");

    /**
     * Constructor for the GoldBlock class.
     * @param isObstacle Indicates whether the block is an obstacle
     * @param imageView The ImageView for the block
     */
    public GoldBlock(boolean isObstacle, ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The value of the block is 250, it is not an end game block, and its weight is 20
        super(250,isObstacle, imageView,false, 20);
    }

    /**
     * Getter for the image of the gold block.
     * @return The image of the gold block
     */
    public static Image getImage() {
        return image;
    }
}