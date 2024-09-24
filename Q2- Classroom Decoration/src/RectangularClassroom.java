public class RectangularClassroom extends Classroom {
    public RectangularClassroom(String name, int width, int height, int length, String walldecoration, String floordecoration) {
        setName(name);
        setWidth(width);
        setHeight(height);
        setLength(length);
        setShape("Rectangle");
        setFloorDecoration(floordecoration);
        setWallDecoration(walldecoration);
    }

    @Override
    public void calculateWall1() {
        setWall1(getWidth() * getLength());
    }

    @Override
    public void calculateWall2() {
        setWall2(getHeight() * getLength());
    }

    @Override
    public void calculateFloorArea() {
        setFloorArea(getLength() * getWidth());
    }
}