package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

@Disabled
@Autonomous(name="supertest", group="Auto") //fix this
public class Test extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    Robot Robot;

    public void init() {
        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        Robot.initialize();
        telemetry.addData("Status","Complete");
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        Robot.DCGm.driveToPosition(0,0,180, runtime);
        telemetry.addData("Status","180Turn");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,1,0, runtime);
        telemetry.addData("Status","Forwards");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,-1,0, runtime);
        telemetry.addData("Status","Backwards");
        telemetry.update();

        Robot.DCGm.driveToPosition(1,0,0, runtime);
        telemetry.addData("Status","Right");
        telemetry.update();

        Robot.DCGm.driveToPosition(-1,0,0, runtime);
        telemetry.addData("Status","Left");
        telemetry.update();

        Robot.DCGm.driveToPosition(0,0,180, runtime);
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

