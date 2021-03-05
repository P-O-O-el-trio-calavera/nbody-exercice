public class NBodySimulator {

    protected Universe universe;
    protected int pauseTime;
    protected boolean trace;
    protected double dt;

    public NBodySimulator(int numBodies){
        this.universe= new Universe(numBodies);
    }

    public NBodySimulator(String fname, double dt, int pauseTime, boolean do_trace){
        this.pauseTime=pauseTime;
        this.dt=dt;
        this.trace=do_trace;
        this.universe= new Universe(fname);
    }

    public void drawUniverse(){
        for (int i = 0; i < universe.bodies.length; i++) {
            StdDraw.setPenRadius(0.025);
            StdDraw.point(universe.bodies[i].r.cartesian(0), universe.bodies[i].r.cartesian(1));
        }

    }

    public void simulate(){
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.point(0.5,0.5);
            StdDraw.setPenColor(StdDraw.WHITE);
            drawUniverse();
            StdDraw.show();
            universe.Update(dt);
            StdDraw.setPenColor(StdDraw.BLACK);
            drawUniverse();
            StdDraw.show();
            StdDraw.pause(0);
        }

    }

    private void CreateCanvas(){

        StdDraw.setXscale(-universe.radi, +universe.radi);
        StdDraw.setYscale(-universe.radi, +universe.radi);

        StdDraw.setCanvasSize(700, 700);

    }

    public static void main(String[] args) {
        NBodySimulator nbody;

        if ((args.length==3) || (args.length==4)) {
            double dt = Double.parseDouble(args[0]);
            String fname = args[1];
            int pauseTime = Integer.parseInt(args[2]);
            boolean do_trace = false;
            if (args.length == 4) {
                do_trace = args[3].toLowerCase().equals("trace");
            }
            nbody = new NBodySimulator(fname, dt, pauseTime, do_trace);
            nbody.CreateCanvas();
            nbody.simulate();
        }
        else if (args.length==1) {
            int numBodies = Integer.parseInt(args[0]);
            nbody = new NBodySimulator(numBodies);
            nbody.CreateCanvas();
            nbody.simulate();
        }
        else {
            assert false : "invalid number of arguments";
        }
    }
}
