package org.firstinspires.ftc.teamcode.team_methods;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

public abstract class TeamMethods extends OpMode {

    public DcMotor motor1 = null;   //RDRIVEFRONT
    public DcMotor motor2 = null;   //LDRIVEFRONT
    public DcMotor motor3 = null;   //LDRIVEBACK
    public DcMotor motor4 = null;   //RDRIVEBACK
    public GyroSensor robotGyro = null;

    //X = a, Y = b
    //The distances are in inches
    private final double Xdistance = 9.27;
    private final double Ydistance = 10.02;
    public double XYcombinedD = Xdistance + Ydistance;

    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    //private static final double     DRIVE_SPEED             = 0.6;
    //private static final double     TURN_SPEED              = 0.5;

    //METHOD 1: self-explanatory
    public void driveToPosition(double inputPosX, double inputPosY, double inputAngle, boolean teleOp) {
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
        V2 = 100*(V2/divisor);
        V3 = 100*(V3/divisor);
        V4 = 100*(V4/divisor);

        double EncoderMax1 = inchToEncoder(0);

        if (teleOp == false) {
            while ( motor1.getCurrentPosition() < V1 ||
                    motor2.getCurrentPosition() < V2 ||
                    motor3.getCurrentPosition() < V3 ||
                    motor4.getCurrentPosition() < V4 ||
                    robotGyro.getHeading() < newInputAngle) {
                motor1.setPower(funcEncoderPercentagePower(motor1.getCurrentPosition(),0, V1));
                motor2.setPower(funcEncoderPercentagePower(motor2.getCurrentPosition(),0, V2));
                motor3.setPower(funcEncoderPercentagePower(motor3.getCurrentPosition(),0, V3));
                motor4.setPower(funcEncoderPercentagePower(motor4.getCurrentPosition(),0, V4));
            }
        } else if (teleOp == true) {
            motor1.setPower(V1);
            motor2.setPower(V2);
            motor3.setPower(V3);
            motor4.setPower(V4);
        }
        telemetry.addData("Motors", "V1 (%.2f), V2 (%.2f), V3 (%.2f), V4 (%.2f)", V1, V2, V3, V4);
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
    //IF undeclared teleop, assumes auto drive method
    public void driveToPosition(double inputPosX, double inputPosY, double inputAngle) {
        driveToPosition(inputPosX,inputPosY,inputAngle,false);
    }

    //METHOD 1.1: basically different input of method driveToPosition, named differently for distinguishing.
    public void strafeToAngle(double angle, double distance, boolean teleOp) {
        double xpos = distance * Math.cos(angle);
        double ypos = distance * Math.sin(angle);
        double inputAngle = 0;
        driveToPosition(xpos,ypos,inputAngle, teleOp);
    }
    //METHOD 1.2: ditto
    public void turnToAngle(double angle, boolean teleOp) { driveToPosition(0,0,angle,teleOp); }

    //FUNCTION 1: handles power scaling as robot approaches near target
    public double funcEncoderPercentagePower(double currentPosition, double encoderFinal, double maxPower) {
        int power = (int)(-100*(currentPosition/encoderFinal)+100);
        if (currentPosition>=encoderFinal){ power=0; }
        if (encoderFinal==0) { power = 0; }
        return power;
    }

    //FUNCTION 2: lots of trig going on, so have fun trying to figure it out
    public double[] relativeValues(double inputX, double inputY, double inputGyro) {
        double angleDifference = robotGyro.getHeading() - inputGyro; //self explanatory
        double angleTriangle = Math.atan(inputY/inputX); //angle of side closest to robot, is added to to get hypotnuse point
        double hypotnuse = Math.sqrt(Math.pow(inputX,2)+Math.pow(inputY,2)); //multiplier to scale cos and sin, keeps values right
        double Xfinal = hypotnuse*Math.cos(angleDifference+angleTriangle);
        double Yfinal = hypotnuse*Math.sin(angleDifference+angleTriangle);
        double returnValues[] = new double[]{Xfinal,Yfinal,angleDifference};
        return returnValues;
    }

    //FUNCTION 3:
    public double inchToEncoder(double inchInput){
        return 0;
    }
    
}