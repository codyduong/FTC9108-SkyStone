package org.firstinspires.ftc.teamcode.pathfinding;

public class GameRegion {
    double X_POS = 0;
    double Y_POS = 0;

    public GameRegion(Node Node1,Node Node2, Node Node3, Node Node4) {
        double X_MIN = Math.min(Math.min(Node1.X_POS, Node2.X_POS), Math.min(Node3.X_POS, Node4.X_POS));
        double X_MAX = Math.max(Math.max(Node1.X_POS, Node2.X_POS), Math.max(Node3.X_POS, Node4.X_POS));
        double Y_MIN = Math.min(Math.min(Node1.Y_POS, Node2.Y_POS), Math.min(Node3.Y_POS, Node4.Y_POS));
        double Y_MAX = Math.min(Math.min(Node1.X_POS, Node2.X_POS), Math.min(Node3.X_POS, Node4.X_POS));

    }


}
