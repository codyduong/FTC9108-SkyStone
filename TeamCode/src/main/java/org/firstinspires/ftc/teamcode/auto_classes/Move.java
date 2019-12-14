package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Move", group="Move")
public class Move extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();
    Robot Robot;

    @Override
    public void runOpMode() {
        /* INITIALIZATION */
        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize();
        telemetry.addData("Status", "Complete");
        telemetry.update();

        //Position 1 is building zone, and position 2 is loading zone.
        waitForStart();
        runtime.reset();

        while(opModeIsActive() && runtime.time((TimeUnit.MILLISECONDS)) < 500) {
            Robot.DCGm.setPower(new double[]{1,1,1,1});
            telemetry.addData("time", runtime.time(TimeUnit.MILLISECONDS));
        }
        Robot.DCGm.setPower(new double[]{0,0,0,0});
        Robot.stop();
    }
}
