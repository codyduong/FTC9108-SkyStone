package org.firstinspires.ftc.teamcode.pathfinding;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;

/*PUBLIC NODE
An class and object which is in essence a position marker.
 */
public class Node {

    public Position2D Pos2D;
    public double X_POS;
    public double Y_POS;

    Node(Position2D Pos2D_I) {
        Pos2D = Pos2D_I;
        X_POS = Pos2D.X_POS;
        Y_POS = Pos2D.Y_POS;
    }

}
