import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * The GameEnvironment class represents the game environment.
 * It sets up the game grid and initializes the world.
 */
public class GameEnvironment {
    public static final int TILE_SIZE = 50; // The size of each tile in the grid
    public static final int WIDTH = 16; // The width of the grid
    public static final int HEIGHT = 15; // The height of the grid
    public ImageView[][] grid = new ImageView[WIDTH][HEIGHT]; // The grid of image views
    public GameBlocks[][] gameBlocks = new GameBlocks[WIDTH][HEIGHT]; // The grid of game blocks

    /**
     * Constructor for the GameEnvironment class.
     * @param root The root pane
     */
    public GameEnvironment(Pane root) {
        Image topSoilImage = new Image("assets\\underground\\top_01.png"); // The image for the top soil
        Random random = new Random(); // Random object for generating random numbers

        // Create the grid and initialize the world
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                // Create a rectangle with a fill color
                Rectangle rectangle = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (y < 2) {
                    rectangle.setFill(Color.MIDNIGHTBLUE); // The top two rows are midnight blue
                } else {
                    rectangle.setFill(Color.SADDLEBROWN); // The rest of the rows are saddle brown
                }
                root.getChildren().add(rectangle); // Add the rectangle to the root pane

                // Create an ImageView for the soil image only if y is greater than or equal to 2
                if (y >= 2) {
                    // Generate a random number to determine the type of block to create
                    int blockType = random.nextInt(100);
                    ImageView imageViewBlock;
                    GameBlocks block;

                    // Depending on the random number, create a different type of block
                    if (blockType < 79) {
                        // Most likely to create a Soil block
                        Soil.fillImageProcessor();
                        Soil.setImage(Soil.getSoilblocksimage()[random.nextInt(Soil.getSoilblocksimage().length)]);
                        imageViewBlock = new ImageView(Soil.getImage());
                        block = new Soil(imageViewBlock);
                    } else if (blockType < 84) {
                        // Less likely to create an EmeraldBlock
                        imageViewBlock = new ImageView(EmeraldBlock.getImage());
                        block = new EmeraldBlock(false, imageViewBlock);
                    } else if (blockType < 88) {
                        // Even less likely to create a DiamondBlock
                        imageViewBlock = new ImageView(DiamondBlock.getImage());
                        block = new DiamondBlock(false, imageViewBlock);
                    } else if (blockType < 90) {
                        // Create a Layerstone block
                        Layerstone.fillImageProcessor();
                        Layerstone.setImage(Layerstone.getLayerStoneImages()[random.nextInt(Layerstone.getLayerStoneImages().length)]);
                        imageViewBlock = new ImageView(Layerstone.getImage());
                        block = new Layerstone(imageViewBlock);
                    } else if (blockType < 97) {
                        // Less likely to create a GoldBlock
                        imageViewBlock = new ImageView(GoldBlock.getImage());
                        block = new GoldBlock(false, imageViewBlock);
                    } else {
                        // The least likely to create a LavaBlock
                        LavaBlock.fillLavaBlocksImage();
                        LavaBlock.setImage(LavaBlock.getLavaBlocksImage()[random.nextInt(LavaBlock.getLavaBlocksImage().length)]);
                        imageViewBlock = new ImageView(LavaBlock.getImage());
                        block = new LavaBlock(0,false, imageViewBlock);
                    }
                    // If the block is at the edge of the grid or at the second row from the top, it is always a Layerstone block
                    if ((x == 0 || x == WIDTH - 1 || y == HEIGHT - 1) && !(y == 2)) {
                        Layerstone.fillImageProcessor();
                        Layerstone.setImage(Layerstone.getLayerStoneImages()[random.nextInt(Layerstone.getLayerStoneImages().length)]);
                        imageViewBlock = new ImageView(Layerstone.getImage());
                        block = new Layerstone(imageViewBlock);
                    }

                    // Set the position of the ImageView based on the grid position
                    imageViewBlock.setX(x * TILE_SIZE);
                    imageViewBlock.setY(y * TILE_SIZE);

                    // If the block is in the second row from the top, it is always a Soil block with a top soil image
                    if (y == 2) {
                        imageViewBlock = new ImageView(topSoilImage);
                        block = new Soil(imageViewBlock);

                        // Adjust the position and size of the ImageView
                        imageViewBlock.setX(x * TILE_SIZE);
                        imageViewBlock.setY(y * TILE_SIZE - 3);
                        imageViewBlock.setFitHeight(TILE_SIZE + 3);
                    }

                    // Add the block to the gameBlocks grid and its ImageView to the grid of ImageViews
                    gameBlocks[x][y] = block;
                    grid[x][y] = block.getImageView();

                    // Add the ImageView to the root pane
                    root.getChildren().add(imageViewBlock);
                }
            }
        }
    }
}