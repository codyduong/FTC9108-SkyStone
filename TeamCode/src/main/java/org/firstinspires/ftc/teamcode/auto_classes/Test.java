package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

@Autonomous(name="supertest", group="Auto") //fix this
public class Test extends OpMode {
    Robot Robot = new Robot(gamepad1,gamepad2);

    public void init() {
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        Robot.initialize(hardwareMap,telemetry);
        telemetry.addData("Status","Complete");
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        Robot.DCGm.driveToPosition(0,0,180);
        telemetry.addData("Status","180Turn");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,1,0);
        telemetry.addData("Status","Forwards");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,-1,0);
        telemetry.addData("Status","Backwards");
        telemetry.update();

        Robot.DCGm.driveToPosition(1,0,0);
        telemetry.addData("Status","Right");
        telemetry.update();

        Robot.DCGm.driveToPosition(-1,0,0);
        telemetry.addData("Status","Left");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,0,180);
        telemetry.addData("Status","180Turn");
        telemetry.update();

        //Robot.DCGm.setPower(new double[]{-100,-100,-100,-100});
    }

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() { }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}

