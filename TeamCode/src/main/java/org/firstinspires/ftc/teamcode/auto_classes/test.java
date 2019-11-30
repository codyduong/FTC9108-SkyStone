package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team_classes.Robot;

@Autonomous(name="supertest", group="Auto") //fix this
public class test extends OpMode {
    Robot Robot = new Robot();

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
        Robot.DCGm.driveToPosition(0,0,180);
        //Robot.DCGm.setPower(new double[]{-100,-100,-100,-100});
        Robot.DCGm.driveToPosition(0,1,0);
        Robot.DCGm.driveToPosition(0,-1,0);
        Robot.DCGm.driveToPosition(1,0,0);
        Robot.DCGm.driveToPosition(-1,0,0);
        Robot.DCGm.driveToPosition(0,0,180);
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

