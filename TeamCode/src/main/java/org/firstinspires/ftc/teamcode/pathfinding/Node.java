package org.firstinspires.ftc.teamcode.pathfinding;

public class Node {
    String type = "";
    double xpos;
    double ypos;
    double xsize;
    double ysize;

    Node(String typeI, double xposI, double yposI, double xsizeI, double ysizeI) {
        xpos = xposI;
        ypos = yposI;
        xsize = xsizeI;
        ysize = ysizeI;
    }

    public double[] GetPos() {
        double returnV[] = new double[]{xpos,ypos};
        return returnV;
    }
}
