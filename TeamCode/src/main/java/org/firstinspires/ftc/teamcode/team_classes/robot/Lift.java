package org.firstinspires.ftc.teamcode.team_classes.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.team_classes.robot.DcMotorGroup;

public class Lift extends DcMotorGroup {
    //Constant Properties
    private static final double COUNTS_PER_MOTOR_REV = 753.2;    // SKU: 5202-0002-0027
    private static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_MM = 100;       // goBilda metric wheels are 100mm
    private static final double WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_MM / 25.4;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    Lift() {
        super(new DcMotor[1]);
    }
    //METHOD 1: self-explanatory
    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.DcMotors[0] = Hmap.get(DcMotor.class, "Lift_motor");
        Tm.addData("Lift Initialization","Complete");
    }

    public void raiseToInch(double inch, double speed){
        double encoderInch = inchToEncoder(inch);
        double direction = inch / java.lang.Math.abs(inch);
        double encoderValue = 0; // Used temporarily.
        /*while (encoderValue < encoderInch){
            this.setPower(new double[] {direction * (speed/100)} );
        }*/
        if (encoderInch>=0) {
            this.DcMotors[0].setTargetPosition((int)encoderInch);
        }
    }

    public void raiseToBlock(double blockNumber, double speed){
        raiseToInch(blockNumber * 4,speed);
    }

    public double inchToEncoder(double inches){
        return inches*COUNTS_PER_INCH;
    }
}