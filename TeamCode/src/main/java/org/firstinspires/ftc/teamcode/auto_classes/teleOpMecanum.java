package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.Robot;


@TeleOp(name="test", group="test") //fix this
public class teleOpMecanum extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot;

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap,telemetry);
        telemetry.addData("Status","Complete");
        telemetry.update();
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
        Position2DAngle relativeValues = Robot.DCG.relativeValues(new Position2DAngle(drivex,drivey,turn), Robot.IMU);
        Robot.DCG.driveToPositionAngle(relativeValues, true);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCG.setPower(new double[]{0,0,0,0});
    }
}
