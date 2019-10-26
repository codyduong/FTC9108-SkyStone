package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.util.concurrent.TimeUnit;

public abstract class teamMethods extends OpMode {

    public DcMotor motor1 = null;   //RDRIVEFRONT
    public DcMotor motor2 = null;   //LDRIVEFRONT
    public DcMotor motor3 = null;   //LDRIVEBACK
    public DcMotor motor4 = null;   //RDRIVEBACK
    public GyroSensor robotGyro = null;

    //X = a, Y = b
    //The distances are in inches
    private final double Xdistance = 9.27;
    private final double Ydistance = 10.02;
    private double XYcombinedD = Xdistance + Ydistance;

    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    //private static final double     DRIVE_SPEED             = 0.6;
    //private static final double     TURN_SPEED              = 0.5;

    public void driveToPosition(double inputPosX, double inputPosY, double inputAngle) {
        double refAngle = robotGyro.getHeading();
        double newInputAngle = refAngle + inputAngle;
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

        while ( motor1.getCurrentPosition() < V1 ||
                motor2.getCurrentPosition() < V2 ||
                motor3.getCurrentPosition() < V3 ||
                motor4.getCurrentPosition() < V4 ||
                robotGyro.getHeading() < newInputAngle) {
            motor1.setPower(funcEncoderPercentagePower(motor1.getCurrentPosition(),V1));
            motor2.setPower(funcEncoderPercentagePower(motor2.getCurrentPosition(),V2));
            motor3.setPower(funcEncoderPercentagePower(motor3.getCurrentPosition(),V3));
            motor4.setPower(funcEncoderPercentagePower(motor4.getCurrentPosition(),V4));
        }
        telemetry.addData("Motors", "V1 (%.2f), V2 (%.2f), V3 (%.2f), V4 (%.2f)", V1, V2, V3, V4);
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
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

    public double funcEncoderPercentagePower(double currentPosition, double encoderFinal) {
        int power = (int)(-100*(currentPosition/encoderFinal)+100);
        if (currentPosition>=encoderFinal){ power=0; }
        if (encoderFinal==0) { power = 0; }
        return power;
    }

}