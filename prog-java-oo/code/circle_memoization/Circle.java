public class Circle {
    public final static Circle UNIT = new Circle(0, 0, 1);
    int x, y, r;
    private Circle(int x, int y, int r) { this.x = x; this.y = y; this.r = r; }

    static Circle create(int x, int y, int r) {
        if (x == 0 && y == 0 && r == 1) return UNIT;
        else  return new Circle(x, y, r);
}  }
