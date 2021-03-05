/******************************************************************************
 *  Compilation:  javac Body.java
 *  Execution:    java Body
 *  Dependencies: Vector.java StdDraw.java
 *
 *  Implementation of a 2D Body with a position, velocity and mass.
 *
 *
 ******************************************************************************/

public class Body {
    protected Vector r;           // position
    protected Vector v;           // velocity
    protected final double mass;  // mass
    private final double G = 6.67e-11;

    public Body(Vector r, Vector v, double mass) {
        this.r = r;
        this.v = v;
        this.mass = mass;
    }

    public void move(Vector f, double dt) {
        Vector a = f.scale(1/mass);
        v = v.plus(a.scale(dt));
        r = r.plus(v.scale(dt));
    }

    public Vector forceFrom(Body other) {
        Vector delta = other.r.minus(this.r);
        double dist = delta.magnitude();
        double magnitude = (G * this.mass * other.mass) / (dist * dist);
        return delta.direction().scale(magnitude);
    }

    @Override
    public String toString() {
        return "position "+r.toString()+", velocity "+v.toString() + ", mass "+mass;
    }
}
