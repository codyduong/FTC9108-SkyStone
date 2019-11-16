package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    public DcMotorGroup(DcMotor[] DcMotorArray) {
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
    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.DcMotors[0] = Hmap.get(DcMotor.class, "right_drive_front");
        this.DcMotors[1] = Hmap.get(DcMotor.class, "left_drive_front");
        this.DcMotors[2] = Hmap.get(DcMotor.class, "left_drive_back");
        this.DcMotors[3] = Hmap.get(DcMotor.class, "right_drive_back");
        this.DcMotors[0].setDirection(DcMotor.Direction.FORWARD);
        this.DcMotors[1].setDirection(DcMotor.Direction.FORWARD);
        this.DcMotors[2].setDirection(DcMotor.Direction.REVERSE);
        this.DcMotors[3].setDirection(DcMotor.Direction.REVERSE);
        Tm.addData("Encoders","Resetting");
        this.DcMotors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.DcMotors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.DcMotors[2].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.DcMotors[3].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.DcMotors[0].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.DcMotors[1].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.DcMotors[2].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.DcMotors[3].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Tm.addData("DcMotorGroup Initialization","Complete");
        Tm.update();
    }

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
    public Position2DAngle relativeValues(Position2DAngle PosAngle, BNOIMU IMU) {
        double THETA_triangle;
        if (PosAngle.X==0) {
            /* The inclusion of 0 as part of the relative operator was deemed unnecessary
             Since the final calculation would suss that out*/
            if (PosAngle.Y > 1) {
                THETA_triangle = 90;
            } else {
                THETA_triangle = -90;
            }
        } else {
            THETA_triangle = Math.atan(PosAngle.Y/PosAngle.X);
        }
        double THETA_relative = (-IMU.getAngle()) + THETA_triangle + PosAngle.ANGLE;
        double L_hypotnuse = PosAngle.getMagnitude();
        double X_New = L_hypotnuse * Math.cos(THETA_relative);
        double Y_New = L_hypotnuse * Math.sin(THETA_relative);
        return new Position2DAngle(X_New,Y_New,THETA_relative);
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
