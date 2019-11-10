package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_methods.DcMotorGroup;

@TeleOp(name="test", group="test") //fix this
public class teleOpMecanum extends OpMode {

    private DcMotor motor1;          //RDRIVEFRONT
    private DcMotor motor2;          //LDRIVEFRONT
    private DcMotor motor3;          //LDRIVEBACK
    private DcMotor motor4;          //RDRIVEBACK
    private DcMotor[] DcMotorArray = new DcMotor[]{motor1,motor2,motor3,motor4};
    private DcMotorGroup MotorGroup = new DcMotorGroup(DcMotorArray);
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
        double relativeValues[] = MotorGroup.relativeValues(new Position2DAngle(drivex,drivey,turn), robotGyro);
        double xNew = relativeValues[1];
        double yNew = relativeValues[2];
        double angleNew = relativeValues[3];
        MotorGroup.driveToPositionAngle(new Position2DAngle(xNew,yNew,angleNew), true);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        MotorGroup.setPower(new double[]{0,0,0,0});
    }
}
