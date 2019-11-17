package org.firstinspires.ftc.teamcode.general_classes;

// Representative of a Pos2D and an angle
public class Position2DAngle {
    public double X;
    public double Y;
    public double ANGLE;

    public Position2DAngle(double x, double y, double angle) {
        X = x;
        Y = y;
        ANGLE = angle;
    }

    public double getMagnitude() {
        double magnitude = Math.hypot(X,Y);
        return magnitude;
    }
}
