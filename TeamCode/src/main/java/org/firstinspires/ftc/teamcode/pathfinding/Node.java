package org.firstinspires.ftc.teamcode.pathfinding;

/*PUBLIC NODE
An class and object which is in essence a position marker.
 */
public class Node {
    double X_POS;
    double Y_POS;

    //object
    Node(double X_POS_I, double Y_POS_I) {
        X_POS = X_POS_I;
        Y_POS = Y_POS_I;
    }

    //returns X and Y pos
    public double[] GetPos() {
        double returnV[] = new double[]{X_POS,Y_POS};
        return returnV;
    }
}
