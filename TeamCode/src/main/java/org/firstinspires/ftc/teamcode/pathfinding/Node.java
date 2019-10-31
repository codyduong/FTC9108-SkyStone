package org.firstinspires.ftc.teamcode.pathfinding;

/*PUBLIC NODE
An class and object which is in essence a position marker.
 */
public class Node {
    double xpos;
    double ypos;

    //object
    Node(double xposI, double yposI) {
        xpos = xposI;
        ypos = yposI;
    }

    //returns X and Y pos
    public double[] GetPos() {
        double returnV[] = new double[]{xpos,ypos};
        return returnV;
    }

    //instantiates several default nodes
    public static void instantiateDefaultField() {

    }


}
