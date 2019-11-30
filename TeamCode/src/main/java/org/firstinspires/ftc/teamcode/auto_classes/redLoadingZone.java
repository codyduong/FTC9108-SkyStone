package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.Robot;

@Autonomous(name="Red Loading Zone", group="Red")
public class redLoadingZone extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot = new Robot();

    public void init() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap, telemetry);
        telemetry.addData("Status", "Complete");
        telemetry.update();
    }

    //Position 1 is building zone, and position 2 is loading zone.
    private int offset = 0;
    private int avarageColorBottom = 0;
    private int averageColorFront = 0;
    private int blockNumber = 0;
    //Initialized by: Start / runs once
    @Override
    public void start() {

        //Starting at wall, facing away, and 50" away from other wall.
        Robot.DCGm.driveToPosition(0, 40, 0);

        while (getRuntime() < 20) {

            offset = 0;
            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                Robot.DCGm.driveToPosition(1, 0, 0);
            }
            offset++;


            //At this point, the robot will pick up the skystone.
            Robot.DCGm.driveToPosition(-offset,   0,   0);
            Robot.DCGm.driveToPosition(0,      -43, 0);
            Robot.DCGm.driveToPosition(-26.75, 0,   0);
            Robot.DCGm.driveToPosition(0,      68,  0);
            Robot.DCGm.driveToPosition(-20,    0,   0);
            Robot.DCGm.driveToPosition(0,      0,   180);
            Robot.DCGl.raiseToBlock(blockNumber,50);
            //At this point, the robot will output the stone in the foundation.
            Robot.DCGl.raiseToBlock(-blockNumber,50);
            Robot.DCGm.driveToPosition(0,     0,   180);
            Robot.DCGm.driveToPosition(20,    0,   0);
            Robot.DCGm.driveToPosition(0,     -68, 0);
            Robot.DCGm.driveToPosition(26.75, 0,   0);
            Robot.DCGm.driveToPosition(0,     43,  0);
        }


        while (Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
            Robot.DCGm.driveToPosition(1, 0, 0);
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


//Initialized by: Start / runs once
