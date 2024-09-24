import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Layerstone class represents a layerstone block in the game.
 * It extends the GameBlocks class and inherits its properties and methods.
 */
public class Layerstone extends GameBlocks{
    // The image for the layerstone block
    private static Image image;

    // Array of images for the layerstone block
    private static final Image[] layerStoneImages = new Image[3];

    /**
     * This static method fills the layerStoneImages array with images.
     */
    public static void fillImageProcessor(){
        for(int i = 0; i < 3; i++){
            layerStoneImages[i] = new Image("assets\\underground\\obstacle_0" + (i+1) + ".png");
        }
    }

    /**
     * Constructor for the Layerstone class.
     * @param imageView The ImageView for the block
     */
    public Layerstone(ImageView imageView) {
        // Call the constructor of the superclass (GameBlocks)
        // The value of the block is 0, it is an obstacle, it is not an end game block, and its weight is 0
        super(0,true, imageView,false, 0);
    }

    /**
     * Getter for the image of the layerstone block.
     * @return The image of the layerstone block
     */
    public static Image getImage() {
        return image;
    }

    /**
     * Setter for the image of the layerstone block.
     * @param image The new image of the layerstone block
     */
    public static void setImage(Image image) {
        Layerstone.image = image;
    }

    /**
     * Getter for the array of images of the layerstone block.
     * @return The array of images of the layerstone block
     */
    public static Image[] getLayerStoneImages(){
        return layerStoneImages;
    }

}