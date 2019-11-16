package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.Robot;

@Autonomous(name="Auto1", group="test") //fix this
public class auto1 extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot;

    public void init() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap,telemetry);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Position 1 is building zone, and position 2 is loading zone.
    private int position = 1;
    private int offset;
    private int averageColorFront;
    private int avarageColorBottom;


    //Initialized by: Start / runs once
    @Override
    public void start() {
        offset = 0;
        avarageColorBottom = 0;
        averageColorFront = 0;
        runtime.reset();

        if (position == 1) {
            //starting facing wall, and 42.25'' away from the other wall
            Robot.DCG.driveToPosition(0, -65.75, 0);
            Robot.DCG.driveToPosition(20, 0, 0);
            Robot.DCG.driveToPosition(0, 47.5, 0);
            Robot.DCG.driveToPosition(0, -47.5, 0);
            Robot.DCG.driveToPosition(-46.75, 0, 0);
            Robot.DCG.driveToPosition(0, -25, 0);

            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                Robot.DCG.driveToPosition(0, -65.75, 0);
                Robot.DCG.driveToPosition(20, 0, 0);
                Robot.DCG.driveToPosition(0, 47.5, 0);
                Robot.DCG.driveToPosition(0, -47.5, 0);
                Robot.DCG.driveToPosition(-46.75, 0, 0);
                Robot.DCG.driveToPosition(0, -25, 0);
            }

            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                Robot.DCG.driveToPosition(-1, 0, 0);
                offset++;
            }

            // at this point, the robot will pick up the skystone
            Robot.DCG.driveToPosition(offset,0,0);
            Robot.DCG.driveToPosition(0,-43,0);
            Robot.DCG.driveToPosition(26.75,0,0);
            Robot.DCG.driveToPosition(0,68,0);
            Robot.DCG.driveToPosition(20,0,0);
            Robot.DCG.driveToPosition(0,0,180);

            //At this point, the robot will place the stone in the foundation.
            Robot.DCG.driveToPosition(0,0,180);
            Robot.DCG.driveToPosition(-20,0,0);
            Robot.DCG.driveToPosition(0,-68,0);

            while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                Robot.DCG.driveToPosition(0,0,180);
                Robot.DCG.driveToPosition(-20,0,0);
                Robot.DCG.driveToPosition(0,-68,0);
            }

            while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                Robot.DCG.driveToPosition(-1,0,0);
            }

            position = 3;
        }
        if (position == 2) {

            //Starting at wall, facing away, and 50" away from other wall.
            Robot.DCG.driveToPosition(0,29,0);

            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                Robot.DCG.driveToPosition(0,29,0);
            }

            while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                Robot.DCG.driveToPosition(-1, 0, 0);
                offset++;
            }

            //At this point, the robot will pick up the skystone.
            Robot.DCG.driveToPosition(offset,0,0);
            Robot.DCG.driveToPosition(0,-43,0);
            Robot.DCG.driveToPosition(26.75,0,0);
            Robot.DCG.driveToPosition(0,68,0);
            Robot.DCG.driveToPosition(20,0,0);
            Robot.DCG.driveToPosition(0,0,180);

            //At this point, the robot will place the stone in the foundation.
            Robot.DCG.driveToPosition(0,0,180);
            Robot.DCG.driveToPosition(-20,0,0);
            Robot.DCG.driveToPosition(0,-68,0);

            while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                Robot.DCG.driveToPosition(0,0,180);
                Robot.DCG.driveToPosition(-20,0,0);
                Robot.DCG.driveToPosition(0,-68,0);
            }

            while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                Robot.DCG.driveToPosition(-1,0,0);
            }
            
            position = 3;
        }

    }


    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {

    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCG.setPower(new double[]{0,0,0,0});
    }
}