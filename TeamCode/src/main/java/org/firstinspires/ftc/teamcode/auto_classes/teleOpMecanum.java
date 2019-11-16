package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_methods.DcMotorGroup;

@TeleOp(name="MecanumDrive2", group="test") //fix this
public class teleOpMecanum extends TeamMethods {
    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor color_sensor;
    //these values should be determined based off hardware being used
    /*
    private static final double ticksPerRev = 1;
    private static final double gearRatio = 1;
    private static final double wheelDiameter = 1;
    private static final double ticksPerInch = (ticksPerRev * gearRatio) / (wheelDiameter * 3.1415); //the math changes on mecanum. fix and find formula.
    */

@TeleOp(name="test", group="test") //fix this
public class teleOpMecanum extends OpMode {

    private DcMotorGroup MotorGroup;
    private GyroSensor robotGyro;

    private ElapsedTime runtime = new ElapsedTime();

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
        frontODS = hardwareMap.colorSensor.get("front_color");
        bottomODS = hardwareMap.colorSensor.get("bottom_color");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

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


        MotorGroup.initialization(hardwareMap,telemetry);
        robotGyro = hardwareMap.get(GyroSensor.class,"gyrosensor");
        robotGyro.calibrate();


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
        Position2DAngle relativeValues = MotorGroup.relativeValues(new Position2DAngle(drivex,drivey,turn), robotGyro);
        MotorGroup.driveToPositionAngle(relativeValues, true);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        MotorGroup.setPower(new double[]{0,0,0,0});
    }
}
