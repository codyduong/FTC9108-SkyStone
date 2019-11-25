package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.Robot;


@Autonomous(name="Old Auto (don't use)", group="test") //This is not to be used anymore
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
    private int avarageColorBottom = 0;
    private int averageColorFront = 0;


    //Initialized by: Start / runs once
    @Override
    public void start() {

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
