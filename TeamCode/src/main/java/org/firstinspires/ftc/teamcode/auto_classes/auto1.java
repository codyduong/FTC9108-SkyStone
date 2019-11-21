package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.Robot;

@Autonomous(name="Auto1", group="test") //fix this
public class auto1 extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot = new Robot();

    public void init() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap,telemetry);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Position 1 is building zone, and position 2 is loading zone.
    private int position = 1;
    private int offset = 0;
    //avarageColorBottom = 0;
    //averageColorFront = 0;


    //Initialized by: Start / runs once
    @Override
    public void start() {

        runtime.reset();

        if (position == 1) {
            //starting facing wall, and 42.25'' away from the other wall
            Robot.DCGm.driveToPosition(0,      -65.75, 0);
            Robot.DCGm.driveToPosition(20,     0,      0);
            Robot.DCGm.driveToPosition(0,      47.5,   0);
            Robot.DCGm.driveToPosition(0,      -47.5,  0);
            Robot.DCGm.driveToPosition(-46.75, 0,      0);
            Robot.DCGm.driveToPosition(0,      -25,    0);


            while (getRuntime() < 20) {
                //while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                //Robot.DCGm.driveToPosition(0,      -65.75, 0);
                //Robot.DCGm.driveToPosition(20,     0,      0);
                //Robot.DCGm.driveToPosition(0,      47.5,   0);
                //Robot.DCGm.driveToPosition(0,      -47.5,  0);
                //Robot.DCGm.driveToPosition(-46.75, 0,      0);
                //Robot.DCGm.driveToPosition(0,      -25,    0);
                //}

                //while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                //Robot.DCGm.driveToPosition(-1, 0, 0);
                //offset++;
                //}

                // at this point, the robot will pick up the skystone
                // Robot.DCGm.driveToPosition(offset,0,0);
                Robot.DCGm.driveToPosition(0,     -43, 0);
                Robot.DCGm.driveToPosition(26.75, 0,   0);
                Robot.DCGm.driveToPosition(0,     68,  0);
                Robot.DCGm.driveToPosition(20,    0,   0);
                Robot.DCGm.driveToPosition(0,     0,   180);

                //At this point, the robot will place the stone in the foundation.
                Robot.DCGm.driveToPosition(0,   0,   180);
                Robot.DCGm.driveToPosition(-20, 0,   0);
                Robot.DCGm.driveToPosition(0,   -68, 0);
                Robot.DCGm.driveToPosition(-26.75, 0,      0);
                Robot.DCGm.driveToPosition(0,      -43,    0);
            }



            //while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                //Robot.DCGm.driveToPosition(0,  0,  180);
                //Robot.DCGm.driveToPosition(-20,0,  0);
                //Robot.DCGm.driveToPosition(0,  -68,0);
            //}

            //while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
               // Robot.DCGm.driveToPosition(-1,0,0);
            //}

            // Open house tape parking
            Robot.DCGm.driveToPosition(18.5,0,0);

            position = 3;
        }
        if (position == 2) {

            //Starting at wall, facing away, and 50" away from other wall.
            Robot.DCGm.driveToPosition(0,40,0);

            //while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                // Robot.DCGm.driveToPosition(0,29,0);
            //}

            while (getRuntime() < 20) {
                //while (Robot.CSG.FrontColorSensor.argb() < averageColorFront) {
                // Robot.DCGm.driveToPosition(-1, 0, 0);
                //offset++;
                //}

                //At this point, the robot will pick up the skystone.
                // Robot.DCGm.driveToPosition(offset,0,0);
                Robot.DCGm.driveToPosition(0,     -43, 0);
                Robot.DCGm.driveToPosition(26.75, 0,   0);
                Robot.DCGm.driveToPosition(0,     68,  0);
                Robot.DCGm.driveToPosition(20,    0,   0);
                Robot.DCGm.driveToPosition(0,     0,   180);

                //At this point, the robot will place the stone in the foundation.
                Robot.DCGm.driveToPosition(0,     0,   180);
                Robot.DCGm.driveToPosition(-20,   0,   0);
                Robot.DCGm.driveToPosition(0,     -68, 0);
                Robot.DCGm.driveToPosition(-26.75,0,   0);
                Robot.DCGm.driveToPosition(0,     43,     0);
            }
            //while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                //Robot.DCGm.driveToPosition(0,0,180);
                //Robot.DCGm.driveToPosition(-20,0,0);
                //Robot.DCGm.driveToPosition(0,-68,0);
            //}

            //while(Robot.CSG.BottomColorSensor.argb() < avarageColorBottom) {
                // Robot.DCGm.driveToPosition(-1,0,0);
            //}


            // Open house tape parking
            Robot.DCGm.driveToPosition(18.5,0,0);
            
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
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}