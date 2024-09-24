public class Gamer {
    private final String name;
    private int point;

    public Gamer(String name) {
        this.name = name;
        this.point = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return point;
    }

    public void addPoint(int points) {
        point += points;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}