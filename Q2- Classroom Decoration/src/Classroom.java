public class Classroom {
    private String name;
    private int width;
    private int height;
    private int length;
    private String shape;
    private double wall1;
    private double wall2;
    private int floorarea;
    private String walltype;
    private String wallDecoration;
    private String floorDecoration;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getLength() {
        return length;
    }
    public String getShape() {
        return shape;
    }
    public double getWall1() {
        return wall1;
    }
    public double getWall2() {
        return wall2;
    }
    public int getFloorArea() {
        return floorarea;
    }
    public void setWall1(double wall1) {
        this.wall1 = wall1;
    }
    public void setWall2(double wall2) {
        this.wall2 = wall2;
    }
    public void setFloorArea(int floorarea) {
        this.floorarea = floorarea;
    }

    public void calculateWall1() {
    }
    public void calculateWall2() {
    }
    public void calculateFloorArea() {
    }
    public String getWallDecoration() {
        return wallDecoration;
    }
    public String getFloorDecoration() {
        return floorDecoration;
    }
    public void setWallDecoration(String wallDecoration) {
        this.wallDecoration = wallDecoration;
    }
    public void setFloorDecoration(String floorDecoration) {
        this.floorDecoration = floorDecoration;
    }
    public void setWalltype(String walltype){
        this.walltype = walltype;
    }
    public String getWalltype(){
        return walltype;
    }
}
