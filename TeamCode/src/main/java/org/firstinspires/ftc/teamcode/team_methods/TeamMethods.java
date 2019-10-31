package org.firstinspires.ftc.teamcode.team_methods;

import static com.qualcomm.robotcore.eventloop.opmode.OpMode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

public abstract class TeamMethods {

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


    
}