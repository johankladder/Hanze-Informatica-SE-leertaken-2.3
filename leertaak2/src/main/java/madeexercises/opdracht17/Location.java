package madeexercises.opdracht17;

/**
 * Created by johankladder on 21-2-16.
 */
public class Location {

    private double x, y;

    private Location center;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location(double x, double y, Location center) {
        this.x = x;
        this.y = y;
        this.center = center;
    }

    public double getX() {
        return  x;
    }

    public double getY() {
        return y;
    }

    public Location getCenter() {
        return center;
    }
}
