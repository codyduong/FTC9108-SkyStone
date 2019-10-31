package org.firstinspires.ftc.teamcode.pathfinding;

/* BoundingBox
Child of Node, creates a bounding box, with the center vertex being the Node,
will "close" any nodes that its intersecting with.
 */
public class BoundingBox extends Node {

    BoundingBox(double xposI, double yposI) {
        super(xposI, yposI);
    }

}
