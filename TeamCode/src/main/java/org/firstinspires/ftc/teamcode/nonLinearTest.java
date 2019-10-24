package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
Okay so I have no idea if we're using mecanum drive, but I made this just for some practice,
and is not tested yet, so is probably broken. Also not finished, just some reference stuff for non-linear.
-Cody D.
 */
@TeleOp(name="test", group="test") //fix this
public class nonLinearTest extends teamMethods {
    private ElapsedTime runtime = new ElapsedTime();

    //these values should be determined based off hardware being used
    /*
    private static final double ticksPerRev = 1;
    private static final double gearRatio = 1;
    private static final double wheelDiameter = 1;
    private static final double ticksPerInch = (ticksPerRev * gearRatio) / (wheelDiameter * 3.1415); //the math changes on mecanum. fix and find formula.
    */

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.addData("Status", "Initializing");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDriveFront  = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveBack = hardwareMap.get(DcMotor.class, "left_drive_back");
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        robotGyro = hardwareMap.get(GyroSensor.class,"gyrosensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveBack.setDirection(DcMotor.Direction.FORWARD);
        rightDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveBack.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Encoders and GyroSensor","Resetting");
        leftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotGyro.calibrate();

        leftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        runtime.reset();
    }


    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        double drivex = -gamepad1.left_stick_y;
        double drivey = gamepad1.left_stick_x;
        double turn  =  gamepad1.right_stick_x;
        driveToPosition(drivex,drivey,0);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {}

}
