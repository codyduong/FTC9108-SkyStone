package org.firstinspires.ftc.teamcode.pathfinding;

/* Obstacle
Child of Node, creates a bounding box, with the center vertex being the Node,
will "close" any nodes that its intersecting with.
 */
public class Obstacle extends Node {
    double X_SIZE;
    double Y_SIZE;
    boolean STATIC;

    Obstacle(Node Node,boolean STATICITY, double X_SIZE_I, double Y_SIZE_I) {
        super(Node.X_POS,Node.Y_POS);
        STATIC = STATICITY;
        X_SIZE = X_SIZE_I;
        Y_SIZE = Y_SIZE_I;
    }
    Obstacle(double X_POS_I, double Y_POS_I, boolean STATICITY, double X_SIZE_I, double Y_SIZE_I) {
        super(X_POS_I,Y_POS_I);
        STATIC = STATICITY;
        X_SIZE = X_SIZE_I;
        Y_SIZE = Y_SIZE_I;
    }

    //instantiates default playing field objects
    public static void instantiateDefaultObstacles() {
        Obstacle B_Skybridge = new Obstacle(0,0,true,0,0);
        Obstacle R_Skybridge = new Obstacle(0,0,true,0,0);
        Obstacle N_SkyBridgL = new Obstacle(0,0,true,0,0);
        Obstacle N_SkyBridgR = new Obstacle(0,0,true,0,0);
    }

    public double[] getSize() {
        double returnValues[] = new double[]{X_SIZE,Y_SIZE};
        return returnValues;
    }

    //will eventually make robotXSize and robotYSize constants, but parameters for now...
    public double minNodeDistance(double robotPos) {

    }
}
