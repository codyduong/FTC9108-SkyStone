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

    private DcMotorGroup MotorGroup;
    private GyroSensor robotGyro;

    private ElapsedTime runtime = new ElapsedTime();

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.addData("Status", "Initializing");
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
