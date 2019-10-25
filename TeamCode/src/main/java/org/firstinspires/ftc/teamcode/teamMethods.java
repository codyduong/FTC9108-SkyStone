package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.util.concurrent.TimeUnit;

public abstract class teamMethods extends OpMode {

    public DcMotor leftDriveFront = null;
    public DcMotor leftDriveBack = null;
    public DcMotor rightDriveFront = null;
    public DcMotor rightDriveBack = null;
    public GyroSensor robotGyro = null;

    //X = a, Y = b
    private final double Xdistance = 8;
    private final double Ydistance = 8;
    private double XYcombinedD = Xdistance + Ydistance;

    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED             = 0.6;
    private static final double     TURN_SPEED              = 0.5;

    public void driveToPosition(double inputPosX, double inputPosY, double inputAngle) {

        //NOTE: This uses displacement instead of velocity, since in practice the ratio of velocity_X to velocity_Y, will be equal to ratio of displacement_X to displacement_Y.
        double V1 = inputPosY - inputPosX + inputAngle*(XYcombinedD);
        double V2 = inputPosY + inputPosX - inputAngle*(XYcombinedD);
        double V3 = inputPosY - inputPosX - inputAngle*(XYcombinedD);
        double V4 = inputPosY + inputPosX + inputAngle*(XYcombinedD);

        double largest = Math.max(Math.max(V1,V2),Math.max(V3,V4));
        double smallest = Math.min(Math.min(V1,V2),Math.min(V3,V4));
        double divisor = Math.max(Math.abs(largest), Math.abs(smallest));

        V1 = 100*(V1/divisor);
        V2 = 100*(V1/divisor);
        V3 = 100*(V1/divisor);
        V4 = 100*(V1/divisor);

        while (rightDriveFront.getCurrentPosition() < V1 || leftDriveFront.getCurrentPosition() < V2 || leftDriveBack.getCurrentPosition() < V3 || rightDriveBack.getCurrentPosition() < V4) {
            rightDriveFront.setPower(V1);
            leftDriveFront.setPower(V2);
            leftDriveBack.setPower(V3);
            rightDriveBack.setPower(V4);

        }
        telemetry.addData("Motors", "V1 (%.2f), V2 (%.2f), V3 (%.2f), V4 (%.2f)", V1, V2, V3, V4);
        rightDriveFront.setPower(0);
        leftDriveFront.setPower(0);
        leftDriveBack.setPower(0);
        rightDriveBack.setPower(0);
    }

    public void strafeToAngle(double angle, double distance) {
        double xpos = distance * Math.cos(angle);
        double ypos = distance * Math.sin(angle);
        double inputAngle = 0;
        driveToPosition(xpos,ypos,inputAngle);
    }

    public void turnToAngle(double angle) {
        driveToPosition(0,0,angle);
    }

    
}