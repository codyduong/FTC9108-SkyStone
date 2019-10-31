package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="test", group="test") //fix this
public class teleOpMecanum extends teamMethods {
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
        motor1 = hardwareMap.get(DcMotor.class, "left_drive_front");
        motor2 = hardwareMap.get(DcMotor.class, "left_drive_back");
        motor3 = hardwareMap.get(DcMotor.class, "right_drive_front");
        motor4 = hardwareMap.get(DcMotor.class, "right_drive_back");
        robotGyro = hardwareMap.get(GyroSensor.class,"gyrosensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2.setDirection(DcMotor.Direction.FORWARD);
        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Encoders and GyroSensor","Resetting");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotGyro.calibrate();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
        double relativeValues[] = relativeValues(drivex,drivey,turn);
        double xNew = relativeValues[1];
        double yNew = relativeValues[2];
        double angleNew = relativeValues[3];
        driveToPosition(xNew,yNew,angleNew, true);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
}
