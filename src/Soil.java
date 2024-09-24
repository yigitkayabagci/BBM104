import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Soil class represents a soil block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class Soil extends GameBlocks {

    // The image for the soil block
    private static Image image;

    // Array of images for the soil block
    private static final Image[] soilblocksimage = new Image[5];

    /**
     * This static method fills the soilblocksimage array with images.
     */
    public static void fillImageProcessor(){
        for(int i = 0; i < 5; i++){
            soilblocksimage[i] = new Image("assets\\underground\\soil_0" + (i+1) + ".png");
        }
    }

    /**
     * Constructor for the Soil class.
     * @param imageView The ImageView for the block
     */
    public Soil(ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The value of the block is 0, it is not an obstacle, it is not an end game block, and its weight is 0
        super(0,false, imageView,false, 0);
    }

    /**
     * Getter for the image of the soil block.
     * @return The image of the soil block
     */
    public static Image getImage() {
        return image;
    }

    /**
     * Setter for the image of the soil block.
     * @param image The new image of the soil block
     */
    public static void setImage(Image image) {
        Soil.image = image;
    }

    /**
     * Getter for the array of images of the soil block.
     * @return The array of images of the soil block
     */
    public static Image[] getSoilblocksimage(){
        return soilblocksimage;
    }

}