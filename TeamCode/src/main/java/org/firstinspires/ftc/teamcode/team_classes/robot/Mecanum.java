package org.firstinspires.ftc.teamcode.team_classes.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.robot.DcMotorGroup;

/**
 * For all stuff Mecanum Drivetrain related
 */
public class Mecanum extends DcMotorGroup {
    public boolean relativeDrive = false;

    //Constant Properties
    private static final double     COUNTS_PER_MOTOR_REV    = 753.2 ;   // SKU: 5202-0002-0027
    private static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_MM       = 100;      // goBilda metric wheels are 100mm
    private static final double     WHEEL_DIAMETER_INCHES   = WHEEL_DIAMETER_MM/25.4;
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    /**
     * Constructor
     */
    public Mecanum() {
        super(new DcMotor[4]);
    }

    /**
     * A method that initializes all of the proper hardware for usage.
     * @param Hmap Hardware Map so we can obtain the DcMotors.
     * @param Tm Telemetry to add data.
     */
    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.DcMotors[0] = Hmap.get(DcMotor.class, "front_right_motor");
        this.DcMotors[1] = Hmap.get(DcMotor.class, "front_left_motor");
        this.DcMotors[2] = Hmap.get(DcMotor.class, "back_left_motor");
        this.DcMotors[3] = Hmap.get(DcMotor.class, "back_right_motor");
        this.DcMotors[0].setDirection(DcMotor.Direction.REVERSE);
        this.DcMotors[1].setDirection(DcMotor.Direction.FORWARD);
        this.DcMotors[2].setDirection(DcMotor.Direction.FORWARD);
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
        this.DcMotors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.DcMotors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.DcMotors[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.DcMotors[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Tm.addData("Mecanum Initialization","Complete");
        Tm.update();
    }




    private final double Xdistance = 10;
    private final double Ydistance = 10;
    private double XYcombinedD = Xdistance + Ydistance;
    /**
     * This drives the robot to a specified position relative to the robot.
     * @param PositionAngle A position and value parameter
     * @param teleOp A boolean for whether this is for teleOp.
     */
    public void driveToPositionAngle(Position2DAngle PositionAngle, boolean teleOp) {

        double relativeY = PositionAngle.Y;
        double relativeX = PositionAngle.X;
        double degreesAngle = PositionAngle.ANGLE;

        //NOTE: This uses displacement instead of velocity, since in practice the ratio of velocity_X to velocity_Y, will be equal to ratio of displacement_X to displacement_Y.
        double V1 = relativeY - relativeX + degreesAngle /* * (XYcombinedD)*/;
        double V2 = relativeY + relativeX - degreesAngle /* * (XYcombinedD)*/;
        double V3 = relativeY - relativeX - degreesAngle /* * (XYcombinedD)*/;
        double V4 = relativeY + relativeX + degreesAngle /* * (XYcombinedD)*/;

        double largest = Math.max((double)Math.max((double)V1,(double)V2),(double)Math.max((double)V3,(double)V4));
        double smallest = Math.min((double)Math.min((double)V1,(double)V2),(double)Math.min((double)V3,(double)V4));
        double divisor = Math.max((double)Math.abs((double)largest), (double)Math.abs((double)smallest));

        double V1n = V1;
        double V2n = V2;
        double V3n = V3;
        double V4n = V4;

        if (V1!=0) {
            V1n = (V1/divisor);
            V2n = (V2/divisor);
            V3n = (V3/divisor);
            V4n = (V4/divisor);
        }
        double EncoderMax = inchToEncoder(Math.hypot(PositionAngle.X,PositionAngle.Y));

        if (!teleOp) {
            //WHILE ENCODER LOOP HERE
            if (divisor == Math.abs(V1)) {
                this.DcMotors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                while (Math.abs(this.DcMotors[0].getCurrentPosition()) < EncoderMax){
                    double m = nonlinearEncoderPercentagePower(this.DcMotors[0].getCurrentPosition(), EncoderMax);
                    this.setPower(new double[]{m*V1n,m*V2n,m*V3n,m*V4n});
                }
                this.setPower(new double[]{0,0,0,0});
            } else if (divisor == Math.abs(V2)) {
                this.DcMotors[1].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                while (Math.abs(this.DcMotors[1].getCurrentPosition()) < EncoderMax){
                    double m = nonlinearEncoderPercentagePower(this.DcMotors[1].getCurrentPosition(), EncoderMax);
                    this.setPower(new double[]{m*V1n,m*V2n,m*V3n,m*V4n});
                }
                this.setPower(new double[]{0,0,0,0});
            } else if (divisor == Math.abs(V3)) {
                this.DcMotors[2].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                while (Math.abs(this.DcMotors[2].getCurrentPosition()) < EncoderMax){
                    double m = nonlinearEncoderPercentagePower(this.DcMotors[2].getCurrentPosition(), EncoderMax);
                    this.setPower(new double[]{m*V1n,m*V2n,m*V3n,m*V4n});
                }
                this.setPower(new double[]{0,0,0,0});
            } else if (divisor == Math.abs(V4)) {
                this.DcMotors[3].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                while (Math.abs(this.DcMotors[3].getCurrentPosition()) < EncoderMax){
                    double m = nonlinearEncoderPercentagePower(this.DcMotors[3].getCurrentPosition(), EncoderMax);
                    this.setPower(new double[]{m*V1n,m*V2n,m*V3n,m*V4n});
                }
                this.setPower(new double[]{0,0,0,0});
            } else {
                this.setPower(new double[]{0,0,0,0});
            }
        } else {
            this.setPower(new double[]{V1n,V2n,V3n,V4n});
        }
    }

    /**
     * This drives the robot to a specified position relative to the robot.
     * Without a teleOp parameter assumes it is autonomous.
     * @param PositionAngle
     */
    public void driveToPositionAngle(Position2DAngle PositionAngle) {
        driveToPositionAngle(PositionAngle,false);
    }

    /**
     * Strafes to a position relative to the bot.
     * @param Position
     * @param teleOp
     */
    public void strafeToAngle(Position2D Position, boolean teleOp) {
        double xlength = Position.X_POS;
        double ylength = Position.Y_POS;
        driveToPositionAngle(new Position2DAngle(xlength,ylength,0), teleOp);
    }

    /**
     * Turns to an angle.
     * @param angle
     * @param teleOp
     */
    public void turnToAngle(double angle, boolean teleOp) {
        driveToPositionAngle(new Position2DAngle(0,0,angle), teleOp);
    }




    /**
     * Deprecated Method that uses individual parameters of X, Y, and Angle individually.
     * New method driveToPositionAngle uses Position2DAngle as a parameter.
     * @param X
     * @param Y
     * @param Angle
     */
    public void driveToPosition(double X, double Y, double Angle) {
        driveToPositionAngle(new Position2DAngle(X,Y,Angle),false);
    }




    /**
     * A function that returns a double from (-1 to 1) that determines the speed of a wheel. Uses a linear formula
     * that calculates the speeds. So it decreases linearly as the encoders reach closer to their desired value.
     *
     * @param currentPosition The current position of the encoder.
     * @param encoderFinal The desired value of the encoder.
     * @param maxPercentagePower The maximum power it scales to. If undeclared is set to 1 by default.
     * @return a double from (-1 to 1)
     */
    public double linearEncoderPercentagePower(double currentPosition, double encoderFinal, double maxPercentagePower) {
        double ratio = (currentPosition/encoderFinal);
        double power = (-maxPercentagePower * ratio) + maxPercentagePower;
        if (encoderFinal==0) { power = 0; }
        if (ratio<=0) { power = maxPercentagePower; }
        if (ratio>=2) { power = -maxPercentagePower; }
        return power;
    }

    public double linearEncoderPercentagePower(double currentPosition, double encoderFinal) {
        double ratio = (currentPosition/encoderFinal);
        double power = (-1 * ratio) + 1;
        if (encoderFinal==0) { power = 0; }
        if (ratio<=0) { power = 1; }
        if (ratio>=2) { power = -1; }
        return power;
    }


    /**
     * A function that returns a double from (-1 to 1) that determines the speed of a wheel. Uses a nonlinear formula
     * that calculates the speeds. It uses cosine to determine the value.
     * @param currentPosition
     * @param encoderFinal
     * @param maxPercentagePower
     * @return
     */
    public double nonlinearEncoderPercentagePower(double currentPosition, double encoderFinal, double maxPercentagePower) {
        double ratio = (currentPosition/encoderFinal);
        double power = maxPercentagePower * Math.cos((Math.PI/2)*ratio);
        if (encoderFinal==0) { power = 0; }
        if (ratio<=0) { power = maxPercentagePower; }
        if (ratio>=2) { power = -maxPercentagePower; }
        return power;
    }

    public double nonlinearEncoderPercentagePower(double currentPosition, double encoderFinal) {
        double ratio = (currentPosition/encoderFinal);
        double power = Math.cos((Math.PI/2)*ratio);
        if (encoderFinal==0) { power = 0; }
        if (ratio<=0) { power = 1; }
        if (ratio>=2) { power = -1; }
        return power;
    }



    /**
     * A function that returns a Position2DAngle value depending on the orientation of the robot.
     * Used for field relative drive.
     *
     * This function is usually reserved for TeleOp.
     * @param PosAngle A gamepad input.
     * @param THETA_robot The current angle of the robot.
     * @return
     */
    private Position2DAngle relativeValues(Position2DAngle PosAngle, double THETA_robot) {
        double THETA_triangle;
        double THETA_subtract = THETA_robot%360;
        if (PosAngle.X==0 && PosAngle.Y!=0) {
            if (PosAngle.Y > 0) {
                THETA_triangle = 90;
            } else {
                THETA_triangle = -90;
            }
        } else if (PosAngle.X!=0 && PosAngle.Y==0) {
            if (PosAngle.X > 0) {
                THETA_triangle = 0;
            } else {
                THETA_triangle = 180;
            }
        } else if (PosAngle.X==0 && PosAngle.Y==0) {
            THETA_triangle = 0;
        } else {
            THETA_triangle = Math.toDegrees(Math.atan2(PosAngle.Y,PosAngle.X));
        }
        double THETA_relative = (THETA_triangle - THETA_subtract);
        double L_hypotnuse = PosAngle.getMagnitude();
        double X_New = L_hypotnuse * Math.cos(Math.toRadians(THETA_relative));
        double Y_New = L_hypotnuse * Math.sin(Math.toRadians(THETA_relative));
        return new Position2DAngle(X_New,Y_New,PosAngle.ANGLE);
    }




    /**
     * Converts from inches to an Encoder Value. Uses constants to determine the encoder value.
     * @param inches The amount of inches to become an encoder value.
     * @return returns the encoder value.
     */
    private double inchToEncoder(double inches){
        return inches*COUNTS_PER_INCH;
    }




    /**
     * Converts an encoder value into a ratio dependent on the unit circle
     * @param encoder
     * @param angle
     * @return
     */
    public double EncoderRatioAngle(double encoder, double angle){
        return 0;
    }

    public void composeTelemetry(Telemetry Tm) {
        Tm.addData("FR_Motor", this.DcMotors[0].getCurrentPosition());
        Tm.addData("FL_Motor", this.DcMotors[1].getCurrentPosition());
        Tm.addData("BL_Motor", this.DcMotors[2].getCurrentPosition());
        Tm.addData("BR_Motor", this.DcMotors[3].getCurrentPosition());
    }

    public void setRelativeDrive(boolean inputBool) {
        relativeDrive = inputBool;
    }

    public void setRelativeDriveToggle() {
        relativeDrive = !relativeDrive;
    }

    public void teleOpDrive(Position2DAngle input, double Heading) {
        if (relativeDrive) {
            Position2DAngle newRelative = relativeValues(input, Heading);
            driveToPositionAngle(newRelative, true);
        } else {
            driveToPositionAngle(input, true);
        }
    }

}
