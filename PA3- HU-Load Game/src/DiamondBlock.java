import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * The DiamondBlock class represents a diamond block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class DiamondBlock extends GameBlocks{

    // The image for the diamond block
    private static final Image image = new Image("assets\\underground\\valuable_diamond.png");

    /**
     * Constructor for the DiamondBlock class.
     * @param isObstacle Indicates whether the block is an obstacle
     * @param imageView The ImageView for the block
     */
    public DiamondBlock(boolean isObstacle, ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The value of the block is 100000, it is not an end game block, and its weight is 100
        super(100000,isObstacle, imageView,false , 100);
    }

    /**
     * Getter for the image of the diamond block.
     * @return The image of the diamond block
     */
    public static Image getImage() {
        return image;
    }
}