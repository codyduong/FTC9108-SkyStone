package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

@Autonomous(name="Blue Loading Zone", group="Blue")
public class BlueLoadingZoneLinear extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();
    Robot Robot;

    @Override
    public void runOpMode() {

        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize();
        telemetry.addData("Status", "Complete");
        telemetry.update();

        //Position 1 is building zone, and position 2 is loading zone.
        int offset = 0;
        int avarageColorBottom = 0;
        int averageColorFront = 0;
        int blockNumber = 0;

        waitForStart();
        runtime.reset();
        //Initialized by: Start / runs once
        //Starting at wall, facing away, and 50" away from other wall.
        Robot.DCGm.driveToPosition(0, 40, 0, runtime);

        while (getRuntime() < 20 && opModeIsActive()) {

            offset = 0;
            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront && opModeIsActive()) {
                Robot.DCGm.driveToPosition(-1, 0, 0, runtime);
                offset++;
            }

            //At this point, the robot will pick up the skystone.
            Robot.SGi.grab();
            Robot.DCGm.driveToPosition(offset,   0,   0, runtime);
            Robot.DCGm.driveToPosition(0,     43,  0, runtime);
            Robot.DCGm.driveToPosition(26.75, 0,   0, runtime);
            Robot.DCGm.driveToPosition(0,     -68, 0, runtime);
            Robot.DCGm.driveToPosition(20,    0,   0, runtime);
            Robot.DCGm.driveToPosition(0,     0,   180, runtime);
            Robot.DCGl.raiseToBlock(blockNumber + 2.25,50);
            Robot.SGi.drop(); //At this point, the robot will output the stone in the foundation.
            Robot.DCGl.raiseToBlock(-blockNumber - 2.25,50);
            blockNumber++;
            Robot.DCGm.driveToPosition(0,      0,  180, runtime);
            Robot.DCGm.driveToPosition(-20,    0,  0, runtime);
            Robot.DCGm.driveToPosition(0,      68, 0, runtime);
            Robot.DCGm.driveToPosition(-26.75, 0,  0, runtime);
            Robot.DCGm.driveToPosition(0,      43, 0, runtime);
        }


        while (Robot.CSG.BottomColorSensor.argb() < avarageColorBottom && opModeIsActive()) {
            Robot.DCGm.driveToPosition(-1, 0, 0, runtime);
        }

        Robot.stop();
    }
}


//Initialized by: Start / runs once
