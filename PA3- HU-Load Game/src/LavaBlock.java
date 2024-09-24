import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * The LavaBlock class represents a lava block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class LavaBlock extends GameBlocks{

    // The image for the lava block
    private static Image image;

    // Array of images for the lava block
    private static final Image[] lavablocksimage = new Image[3];

    /**
     * This static method fills the lavablocksimage array with images.
     */
    public static void fillLavaBlocksImage(){
        for(int i = 0; i < 3; i++){
            lavablocksimage[i] = new Image("assets\\underground\\lava_0" + (i + 1) + ".png");
        }
    }

    /**
     * Constructor for the LavaBlock class.
     * @param value The value of the block
     * @param isObstacle Indicates whether the block is an obstacle
     * @param imageView The ImageView for the block
     */
    public LavaBlock(int value,boolean isObstacle, ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The block is an end game block and its weight is 0
        super(value,isObstacle, imageView,true, 0);
    }

    /**
     * Getter for the image of the lava block.
     * @return The image of the lava block
     */
    public static Image getImage() {
        return image;
    }

    /**
     * Setter for the image of the lava block.
     * @param image The new image of the lava block
     */
    public static void setImage(Image image) {
        LavaBlock.image = image;
    }

    /**
     * Getter for the array of images of the lava block.
     * @return The array of images of the lava block
     */
    public static Image[] getLavaBlocksImage(){
        return lavablocksimage;
    }

}