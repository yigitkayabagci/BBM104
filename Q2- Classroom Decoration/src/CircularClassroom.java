public class CircularClassroom extends Classroom{
    public CircularClassroom(String name, int width, int height, int length, String walldecoration, String floordecoration) {
        setName(name);
        setWidth(width);
        setHeight(height);
        setLength(length);
        setShape("Circle");
        setFloorDecoration(floordecoration);
        setWallDecoration(walldecoration);
    }

    @Override
    public void calculateWall1() {
        setWall1((Math.PI * (getWidth()) * getLength()));
    }

    @Override
    public void calculateWall2() {
        setWall2(( Math.PI * (getHeight()) * getLength()));
    }

    @Override
    public void calculateFloorArea() {
        setFloorArea((int) Math.round(Math.PI * (getWidth() / 2)));
    }
}