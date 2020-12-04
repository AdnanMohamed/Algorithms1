import java.util.*;


public class BruteCollinearPoints {

    private List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        segments = new ArrayList<>();
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                for (int k = j + 1; k < points.length; ++k) {
                    for (int l = k + 1; l < points.length; ++l) {
                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[l]);

                        if ((slope1 == slope2 && slope1 == slope3)
                            || (Double.isInfinite(slope1) && Double.isInfinite(slope2) && Double.isInfinite(slope3))){
                            segments.add(new LineSegment(points[min(points, i, j, k, l)], points[max(points, i, j, k, l)]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private void validate(Point[] pts) {
        if (pts == null) {
            throw new IllegalArgumentException("The array of points is null.");
        }

        for (Point p : pts) {
            if (p == null) {
                throw new IllegalArgumentException("There is a null point");
            }
        }

        for (int i = 0; i < pts.length; ++i) {
            for (int j = i + 1; j < pts.length; ++j) {
                if (pts[i].compareTo(pts[j])==0)
                    throw new IllegalArgumentException("There are duplicate points.");
            }
        }
    }

    private int max(Point[] pts, int i1, int i2, int i3, int i4) {
        int maxIndex = i1;
        if (pts[i2].compareTo(pts[maxIndex]) > 0) maxIndex = i2;
        if (pts[i3].compareTo(pts[maxIndex]) > 0) maxIndex = i3;
        if (pts[i4].compareTo(pts[maxIndex]) > 0) maxIndex = i4;
        return maxIndex;
    }

    private int min(Point[] pts, int i1, int i2, int i3, int i4) {
        int minIndex = i1;
        if (pts[i2].compareTo(pts[minIndex]) < 0) minIndex = i2;
        if (pts[i3].compareTo(pts[minIndex]) < 0) minIndex = i3;
        if (pts[i4].compareTo(pts[minIndex]) < 0) minIndex = i4;

        return minIndex;
    }

    public static void main(String[] args) {
//        final int size = 13;
//        Point[] pts = new Point[size];
//        Random rand = new Random();
//
//        Set<Point> set = new HashSet<Point>();
//        int i = 0;
//        while (set.size() < size) {
//            int x = rand.nextInt(5);
//            int y = rand.nextInt(5);
//            Point p = new Point(x, y);
//            if (!set.contains(p)) {
//                set.add(p);
//                pts[i++] = p;
//            }
//        }
//        System.out.println("Printing the points:-");
//        for (Point p : pts) {
//            System.out.print(p + ", ");
//        }
//        System.out.println();
//
//        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(pts);
//        if (collinearPoints.numberOfSegments() > 0) {
//            System.out.println("Printing the points making a segment:-");
//            for (LineSegment lineSegment : collinearPoints.segments()) {
//                System.out.println(lineSegment);
//            }
//        }
    }
}