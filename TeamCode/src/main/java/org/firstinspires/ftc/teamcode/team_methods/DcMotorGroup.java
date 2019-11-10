package org.firstinspires.ftc.teamcode.team_methods;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;

public class DcMotorGroup {

    //Properties
    public DcMotor[] DcMotors;
    public int DcMotorCount;

    //Constant Properties
    private static final double     COUNTS_PER_MOTOR_REV    = 0 ;       // 5202 Series Yellow Jacket Planetary Gear Motor
    private static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_MM       = 100;      // goBilda metric wheels are 100mm
    private static final double     WHEEL_DIAMETER_INCHES   = WHEEL_DIAMETER_MM/25.4;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //Constructor
    DcMotorGroup(DcMotor[] DcMotorArray) {
        DcMotors = DcMotorArray;
        DcMotorCount = DcMotorArray.length;
    }

    //setPower to all of the motors
    public void setPower(double[] power) {
        for(int i=0; i<DcMotorCount; i++) {
            this.DcMotors[i].setPower(power[i]);
        }
    }

    //METHOD 1: self-explanatory
    public void driveToPositionAngle(Position2DAngle PositionAngle, boolean teleOp) {

        //X = a, Y = b
        //The distances are in inches
        final double Xdistance = 9.27;
        final double Ydistance = 10.02;
        double XYcombinedD = Xdistance + Ydistance;

        double relativeY = PositionAngle.X;
        double relativeX = PositionAngle.Y;
        double degreesAngle = PositionAngle.ANGLE;

        //NOTE: This uses displacement instead of velocity, since in practice the ratio of velocity_X to velocity_Y, will be equal to ratio of displacement_X to displacement_Y.
        double V1 = relativeY - relativeX + degreesAngle*(XYcombinedD);
        double V2 = relativeY + relativeX - degreesAngle*(XYcombinedD);
        double V3 = relativeY - relativeX - degreesAngle*(XYcombinedD);
        double V4 = relativeY + relativeX + degreesAngle*(XYcombinedD);

        double largest = Math.max(Math.max(V1,V2),Math.max(V3,V4));
        double smallest = Math.min(Math.min(V1,V2),Math.min(V3,V4));
        double divisor = Math.max(Math.abs(largest), Math.abs(smallest));

        V1 = 100*(V1/divisor);
        V2 = 100*(V2/divisor);
        V3 = 100*(V3/divisor);
        V4 = 100*(V4/divisor);

        double EncoderMax1 = inchToEncoder(0);

        if (teleOp == false) {
            //WHILE ENCODER LOOP HERE
        } else if (teleOp == true) {
            this.setPower(new double[]{V1,V2,V3,V4});
        }

    }
    //IF undeclared teleop, assumes auto drive method
    public void driveToPositionAngle(Position2DAngle PositionAngle) {
        driveToPositionAngle(PositionAngle,false);
    }

    //METHOD 1.1: basically different input of method driveToPosition, named differently for distinguishing.
    public void strafeToAngle(Position2D Position, boolean teleOp) {
        double xlength = Position.X_POS;
        double ylength = Position.Y_POS;
        driveToPositionAngle(new Position2DAngle(xlength,ylength,0), teleOp);
    }
    //METHOD 1.2: ditto
    public void turnToAngle(double angle, boolean teleOp) {
        driveToPositionAngle(new Position2DAngle(0,0,angle), teleOp);
    }

    //DEPRECATED METHOD KEPT FOR EASE OF CODE
    public void driveToPosition(double X, double Y, double Angle) {
        driveToPositionAngle(new Position2DAngle(X,Y,Angle),false);
    }
    //FUNCTION 1: handles power scaling as robot approaches near target
    public double funcEncoderPercentagePower(double currentPosition, double encoderFinal, double maxPower) {
        int power = (int)(-100*(currentPosition/encoderFinal)+100);
        if (currentPosition>=encoderFinal){ power=0; }
        if (encoderFinal==0) { power = 0; }
        return power;
    }

    //FUNCTION 2: lots of trig going on, so have fun trying to figure it out
    public double[] relativeValues(Position2DAngle PosAngle, GyroSensor robotGyro) {
        double angleDifference = robotGyro.getHeading() - PosAngle.ANGLE; //self explanatory
        double angleTriangle = Math.atan(PosAngle.Y/PosAngle.X); //angle of side closest to robot, is added to to get hypotnuse point
        double hypotnuse = Math.sqrt(Math.pow(PosAngle.X,2)+Math.pow(PosAngle.Y,2)); //multiplier to scale cos and sin, keeps values right
        double Xfinal = hypotnuse*Math.cos(angleDifference+angleTriangle);
        double Yfinal = hypotnuse*Math.sin(angleDifference+angleTriangle);
        double returnValues[] = new double[]{Xfinal,Yfinal,angleDifference};
        return returnValues;
    }

    //FUNCTION 3:
    public double inchToEncoder(double inches){
        return inches*COUNTS_PER_INCH;
    }

    //FUNCTION 4:
    public double EncoderRatioAngle(double encoder, double Angle){
        return 0;
    }
}
