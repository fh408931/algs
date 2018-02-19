package algorithms.fundamentals.dataAbstraction;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author 
 *1.2.1编写一个Point2D的用例，从命令行接受一个整数N。在单位正方形内生成N个随机点，然后计算两点之间的最近距离
 */
public class Point2D implements Comparable<Point2D>{
	//用x坐标比较两点。
	public static final Comparator<Point2D> X_ORDER = new XOrder();
	//用y坐标比较两点。
	public static final Comparator<Point2D> Y_ORDER = new YOrder();
	//用半径比较两点。
	public static final Comparator<Point2D> R_ORDER = new ROrder();
	
	 public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
	 public final Comparator<Point2D> ATAN2_ORDER = new Atan2Order();
	 public final Comparator<Point2D> DISTANCE_TO_ORDER = new DistanceToOrder();

	private final double x;
	private final double y;
	
	public Point2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double x(){return x;}
	public double y(){return y;}
	
	public double r() { return Math.sqrt(x*x + y*y); }
	//以极坐标返回此点的角度。
	public double theta(){ return Math.atan2(y, x);}

	//返回该点和该点之间的极角（在-pi和pi之间）;
	//（如果两点相等，则为0）
    private double angleTo(Point2D that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }

//    是a-> b-> c逆时针转动？
//     顺时针旋转-1，逆时针旋转+1，共线旋转0	
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }

    // twice signed area of a-b-c
    public static double area2(Point2D a, Point2D b, Point2D c) {
        return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
    }

    // return Euclidean distance between this point and that point
    public double distanceTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // return square of Euclidean distance between this point and that point
    public double distanceSquaredTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx*dx + dy*dy;
    }

    // compare by y-coordinate, breaking ties by x-coordinate
    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    // compare points according to their x-coordinate, breaking ties by y-coordinate
    private static class XOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }

    // compare points according to their y-coordinate, breaking ties by x-coordinate
    private static class YOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            if (p.x < q.x) return -1;
            if (p.x > q.x) return +1;
            return 0;
        }
    }

    // compare points according to their polar radius
    private static class ROrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            double delta = p.x*p.x - q.x*q.x;
            if (delta < 0) return -1;
            if (delta > 0) return +1;
            return 0;
        }
    }
 
    // compare other points relative to atan2 angle (bewteen -pi/2 and pi/2) they make with this Point
    private class Atan2Order implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            if      (angle1 < angle2) return -1;
            else if (angle1 > angle2) return +1;
            else                      return  0;
        }
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    // compare points according to their distance to this point
    private class DistanceToOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            double dist1 = distanceSquaredTo(p);
            double dist2 = distanceSquaredTo(q);
            if      (dist1 < dist2) return -1;
            else if (dist1 > dist2) return +1;
            else                    return  0;
        }
    }


    // does this point equal y?
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }

    // convert to string
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // plot using StdDraw
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line from this point p to q using StdDraw
    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public static void main(String[] args) {
        int x0 = Integer.parseInt(args[0]);
        int y0 = Integer.parseInt(args[1]);
        int N = Integer.parseInt(args[2]);

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        // draw p = (x0, x1) in red
        Point2D p = new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(.02);
        p.draw();


        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Arrays.sort(points, p.POLAR_ORDER);
        for (int i = 0; i < N; i++) {
            p.drawTo(points[i]);
            StdDraw.show(100);
        }
    }
}
