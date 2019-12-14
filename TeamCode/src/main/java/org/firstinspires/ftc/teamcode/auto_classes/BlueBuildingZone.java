package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

@Autonomous(name="Blue Building Zone", group="Blue")
public class BlueBuildingZone extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot;

    public void init() {
        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize();
        telemetry.addData("Status", "Complete");
        telemetry.update();
    }

    //Position 1 is building zone, and position 2 is loading zone.
    private int offset;
    private int avarageColorBottom = 0;
    private int averageColorFront = 0;
    private int blockNumber = 0;
    //Initialized by: Start / runs once
    @Override
    public void start() {

        runtime.reset();


        //starting facing wall, and 42.25'' away from the other wall
        Robot.DCGm.driveToPosition(0,      65.75, 0, 5000);
        Robot.DCGm.driveToPosition(20,     0,      0, 5000);
        Robot.DCGm.driveToPosition(0,      -47.5,   0, 5000);
        Robot.DCGm.driveToPosition(0,      47.5,  0, 5000);
        Robot.DCGm.driveToPosition(-46.75, 0,      0, 5000);
        Robot.DCGm.driveToPosition(0,      25,    0, 5000);


        while (getRuntime() < 20) {

            offset = 0;
            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
            Robot.DCGm.driveToPosition(-1, 0, 0, 5000);
            offset++;
            }

            Robot.SGi.grab(); // at this point, the robot will intake the skystone
            Robot.DCGm.driveToPosition(offset,   0,   0, 5000);
            Robot.DCGm.driveToPosition(0,     43,  0, 5000);
            Robot.DCGm.driveToPosition(26.75, 0,   0, 5000);
            Robot.DCGm.driveToPosition(0,     -68, 0, 5000);
            Robot.DCGm.driveToPosition(20,    0,   0, 5000);
            Robot.DCGm.driveToPosition(0,     0,   180, 5000);
            Robot.DCGl.raiseToBlock(blockNumber + 2.25,50);
            Robot.SGi.drop(); // self-explanatory
            Robot.DCGl.raiseToBlock(-blockNumber - 2.25,50);
            blockNumber++;	            blockNumber++;
            Robot.DCGm.driveToPosition(0,      0,  180, 5000);
            Robot.DCGm.driveToPosition(-20,    0,  0, 5000);
            Robot.DCGm.driveToPosition(0,      68, 0, 5000);
            Robot.DCGm.driveToPosition(-26.75, 0,  0, 5000);
            Robot.DCGm.driveToPosition(0,      43, 0, 5000);
        }

        while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
            Robot.DCGm.driveToPosition(-1,0,0, 5000);
        }
    }


    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {

    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}
