package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class teamMethods extends OpMode {
    public void driveToPosition(double inputPosX,double inputPosY) {
        //The Xdistance and Ydistance is marked on the above image
        final double Xdistance = 1;
        final double Ydistance = 1;
        double XYcombinedD = Xdistance + Ydistance;
        double gyroRobot = obtainGyroInput;

        double V1 = inputPosY - inputPosX + gyroRobot*(XYcombinedD);
        double V2 = inputPosY + inputPosX - gyroRobot*(XYcombinedD);
        double V3 = inputPosY - inputPosX - gyroRobot*(XYcombinedD);
        double V4 = inputPosY + inputPosX + gyroRobot*(XYcombinedD);

        double compareSet1;
        double compareSet2;
        double compareSet1s;
        double compareSet2s;
        double largest;
        double smallest;
        if (V1 >= V2) {
            compareSet1 = V1;
            compareSet1s = V2;
        } else {
            compareSet1 = V2;
            compareSet1s = V1;
        }
        if (V3 >= V4) {
            compareSet2 = V3;
            compareSet2s = V4;
        } else {
            compareSet2 = V4;
            compareSet2s = V3;
        }
        if (compareSet1>=compareSet2) {
            largest = compareSet1;
        } else {
            largest = compareSet2;
        }
        if (compareSet1s<=compareSet2s) {
            smallest = compareSet1s;
        } else {
            smallest = compareSet2s;
        }

        double difference = largest-smallest
        double V1F =
        double V2F =
        double V3F =
        double V4F =

    }
}