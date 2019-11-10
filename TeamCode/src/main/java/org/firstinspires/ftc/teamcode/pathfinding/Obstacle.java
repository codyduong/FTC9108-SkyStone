package org.firstinspires.ftc.teamcode.pathfinding;

import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Size2D;

/* Obstacle
Child of Node, creates a bounding box, with the center vertex being the Node,
will "close" any nodes that its intersecting with.
 */
public class Obstacle extends Node {

    public Position2D Pos2D;
    public double X_POS;
    public double Y_POS;
    public Size2D Size2D;
    public double X_SIZE;
    public double Y_SIZE;

    public Obstacle(Node Node_I, Size2D Size2D_I, double Rotation_I, boolean STATICITY_I) {
        super(Node_I.Pos2D);
        Pos2D = Node_I.Pos2D;
        X_POS = Pos2D.X_POS;
        Y_POS = Pos2D.Y_POS;
        Size2D = Size2D_I;
        X_SIZE = Size2D.X_SIZE;
        X_SIZE = Size2D.Y_SIZE;
    }

    public Obstacle(Position2D Pos2D_I, Size2D Size2D_I, double Rotation_I, boolean STATICITY_I) {
        super(Pos2D_I);
        Pos2D = Pos2D_I;
        X_POS = Pos2D.X_POS;
        Y_POS = Pos2D.Y_POS;
        Size2D = Size2D_I;
        X_SIZE = Size2D.X_SIZE;
        X_SIZE = Size2D.Y_SIZE;
    }

    //instantiates default playing field objects
    public static void instantiateDefaultObstacles() {
        /*
        Obstacle B_Skybridge = new Obstacle(0,0,true,0,0);
        Obstacle R_Skybridge = new Obstacle(0,0,true,0,0);
        Obstacle N_SkyBridgL = new Obstacle(0,0,true,0,0);
        Obstacle N_SkyBridgR = new Obstacle(0,0,true,0,0);
         */
    }

    //will eventually make robotXSize and robotYSize constants, but parameters for now...
    public double minNodeDistance(double robotPos) {
        return 0;
    }
}
