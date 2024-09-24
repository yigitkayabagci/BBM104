import javafx.scene.image.Image;

public class Miner {

    private final GameEnvironment gameEnvironment; // The game environment
    private int x; // The x-coordinate of the miner
    private int y; // The y-coordinate of the miner
    private boolean isFlying = true; // Flag to check if the miner is flying
    private Image image; // The image of the miner
    private boolean isAccelerating = false; // Flag to check if the miner is accelerating

    private int offsetX; // The x-offset for drawing the miner image
    private int offsetY; // The y-offset for drawing the miner image

    private String imagePath; // The path of the miner image
    private int gravityCounter = 0; // Counter for applying gravity

    /**
     * Constructor for the Miner class.
     * @param gameEnvironment The game environment
     */
    public Miner(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.x = GameEnvironment.WIDTH / 2; // Start in the middle of the grid
        this.y = 1;
        this.image = new Image("assets\\drill\\drill_01.png");
        this.imagePath = "assets\\drill\\drill_01.png";
        setOffsets();
    }

    // Method for moving down to miner
    public void digDown() {
        if (y < GameEnvironment.HEIGHT - 1 && gameEnvironment.grid[x][y+1] != null && (!gameEnvironment.gameBlocks[x][y+1].getIsObstacle())){
            if (gameEnvironment.gameBlocks[x][y+1].isEndGame()){
                Main.setGameOver(true);
            }
            Main.totalFuel -= 100;
            Main.collectedMoney += gameEnvironment.gameBlocks[x][y+1].getValue();
            Main.totalWeight += gameEnvironment.gameBlocks[x][y+1].getWeight();
            y++;
            gameEnvironment.grid[x][y].setImage(null); // Remove the soil image
            gameEnvironment.grid[x][y] = null; // Set the grid cell to null
            this.image = new Image("assets\\drill\\drill_44.png");
            this.imagePath = "assets\\drill\\drill_44.png";
            setOffsets();
        }
    }
    // Method for moving left to miner
    public void moveLeft() {
        if (x > 0) {
            if (gameEnvironment.grid[x-1][y] == null) {
                this.image = new Image("assets\\drill\\drill_01.png");
                this.imagePath = "assets\\drill\\drill_01.png";
                setOffsets();
                x--;
                Main.totalFuel -= 100;
            } else if (!isFlying && gameEnvironment.grid[x-1][y] != null && (!gameEnvironment.gameBlocks[x-1][y].getIsObstacle())) {
                if (gameEnvironment.gameBlocks[x-1][y].isEndGame()){
                    Main.setGameOver(true);
                }
                Main.totalFuel -= 100;
                this.image = new Image("assets\\drill\\drill_01.png");
                this.imagePath = "assets\\drill\\drill_01.png";
                setOffsets();
                gameEnvironment.grid[x-1][y].setImage(null); // Remove the soil image
                gameEnvironment.grid[x-1][y] = null; // Set the grid cell to null
                Main.collectedMoney += gameEnvironment.gameBlocks[x-1][y].getValue();
                Main.totalWeight += gameEnvironment.gameBlocks[x-1][y].getWeight();
                x--;
            }
        }
    }

    // Method for moving right to miner
    public void moveRight() {
        if (x < GameEnvironment.WIDTH - 1) {
            if (gameEnvironment.grid[x+1][y] == null) {
                this.image = new Image("assets\\drill\\drill_right\\01.png");
                this.imagePath = "assets\\drill\\drill_right\\01.png";
                setOffsets();
                x++;
                Main.totalFuel -= 100;
            } else if (!isFlying && gameEnvironment.grid[x+1][y] != null && (!gameEnvironment.gameBlocks[x+1][y].getIsObstacle())) {
                if (gameEnvironment.gameBlocks[x+1][y].isEndGame()){
                    Main.setGameOver(true);
                }
                Main.totalFuel -= 100;
                Main.collectedMoney += gameEnvironment.gameBlocks[x+1][y].getValue();
                Main.totalWeight += gameEnvironment.gameBlocks[x+1][y].getWeight();
                this.image = new Image("assets\\drill\\drill_right\\01.png");
                this.imagePath = "assets\\drill\\drill_right\\01.png";
                setOffsets();
                gameEnvironment.grid[x+1][y].setImage(null); // Remove the soil image
                gameEnvironment.grid[x+1][y] = null; // Set the grid cell to null
                x++;

            }
        }
    }
    // Method for moving up to miner
    public void flyUp() {
        if (y > 0 && gameEnvironment.grid[x][y-1] == null) {
            isFlying = true; // Uçma tuşuna basıldığında isFlying'ı true olarak ayarlayın
            isAccelerating = true;
            Main.totalFuel -= 100;
            y--;
            this.image = new Image("assets\\drill\\drill_26.png");
            this.imagePath = "assets\\drill\\drill_26.png";
            setOffsets();
        }
    }

    /**
     * Checks if the miner is flying. The miner is considered flying if there is no block beneath it.
     */
    public void checkFlying(){
        if (y < GameEnvironment.HEIGHT - 1) {
            this.isFlying = gameEnvironment.grid[x][y + 1] == null;
        }
    }

    /**
     * Applies gravity to the miner. If the miner is flying and not accelerating, it will fall down after a certain amount of time.
     */
    public void applyGravity() {
        gravityCounter++;
        if (gravityCounter > 40 && isFlying) { // If isAccelerating is false, apply gravity when gravityCounter is more than 30
            if (y < GameEnvironment.HEIGHT - 1 && gameEnvironment.grid[x][y+1] == null) {
                y++;
            }
            gravityCounter = 0;
        }
    }
    /**
     * Sets the offsets for drawing the miner image. The offsets depend on the current image of the miner.
     */
    public void setOffsets() {
        if (this.imagePath.contains("drill_01.png")) {
            this.offsetX = -20; // Set the offset values as needed
            this.offsetY = -10;
        } else if (this.imagePath.contains("drill\\drill_right\\01.png")) {
            this.offsetX = 0;
            this.offsetY = -10;
        } else if (this.imagePath.contains("drill_44.png")) {
            this.offsetX = -20;
            this.offsetY = -6;
        } else if (this.imagePath.contains("drill_26.png")) {
            this.offsetX = -20;
            this.offsetY = -8;
        }
    }


    // Getter methods
    public int getX() { return x; } // Returns the x-coordinate of the miner
    public int getY() { return y; } // Returns the y-coordinate of the miner
    public boolean isFlying() { return isFlying; } // Returns true if the miner is flying, false otherwise
    public boolean isAccelerating() { return isAccelerating; } // Returns true if the miner is accelerating, false otherwise
    public Image getImage() { return image; } // Returns the image of the miner
    public int getOffsetX() { return offsetX; } // Returns the x-offset for drawing the miner image
    public int getOffsetY() { return offsetY; } // Returns the y-offset for drawing the miner image

    /**
     * Sets the accelerating status of the miner.
     * @param accelerating The accelerating status to set
     */
    public void setAccelerating(boolean accelerating) {
        isAccelerating = accelerating;
    }
}