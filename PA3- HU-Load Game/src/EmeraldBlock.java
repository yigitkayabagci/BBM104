import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * The EmeraldBlock class represents an emerald block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class EmeraldBlock extends GameBlocks{

    // The image for the emerald block
    private static final Image image = new Image("assets\\underground\\valuable_emerald.png");

    /**
     * Constructor for the EmeraldBlock class.
     * @param isObstacle Indicates whether the block is an obstacle
     * @param imageView The ImageView for the block
     */
    public EmeraldBlock(boolean isObstacle, ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The value of the block is 5000, it is not an end game block, and its weight is 60
        super(5000,isObstacle, imageView,false, 60);
    }

    /**
     * Getter for the image of the emerald block.
     * @return The image of the emerald block
     */
    public static Image getImage() {
        return image;
    }
}