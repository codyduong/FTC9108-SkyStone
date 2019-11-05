package org.firstinspires.ftc.teamcode.pathfinding;
import org.firstinspires.ftc.teamcode.general_classes.Pos2D;

/*PUBLIC NODE
An class and object which is in essence a position marker.
 */
public class Node {
    public Pos2D Pos2D;
    double X_POS;
    double Y_POS;

    //object
    Node(Pos2D Pos2DInput) {
        X_POS = Pos2DInput.X_POS;
        Y_POS = Pos2DInput.Y_POS;
        Pos2D = Pos2DInput;
    }

    //returns X and Y pos
    public Pos2D getPos2D() {
        return Pos2D;
    }
}
