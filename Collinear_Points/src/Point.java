import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final Integer x, y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        else if (this.y > that.y) return 1;
        else return this.x - that.x;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        int delta_y = this.y - that.y;
        int delta_x = this.x - that.x;
        return ((double)(delta_y)) / delta_x;
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new PointCmptor();
    }

    private class PointCmptor implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            Point p0 = new Point(x, y);
            if (p0.slopeTo(p1) < p0.slopeTo(p2)) return -1;
            if (p0.slopeTo(p1) > p0.slopeTo(p2)) return 1;
            return 0;
        }
    }

    // for debugging.
    @Override
    public boolean equals(Object that) {
        return this.compareTo((Point)that) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = (hash * 397) ^ x.hashCode();
        hash = (hash * 397) ^ y.hashCode();
        return hash;
    }
}