package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.Robot;


@TeleOp(name="Luke", group="Driver") //fix this
public class driver_luke extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot = new Robot();

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot.initialize(hardwareMap,telemetry);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        runtime.reset();
    }

    boolean on;

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        //Joysticks
        double drivey = gamepad1.left_stick_y;
        double drivex = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double PERCENTAGE = Math.hypot(drivey, drivex);
        if (Math.abs((double)drivey) < .05) {
            drivey = 0;
        }
        if (Math.abs((double)drivex) < .05) {
            drivex = 0;
        }
        if (Math.abs((double)turn) < .05) {
            turn = 0;
        }
        double Heading = (Math.toDegrees(Robot.IMU.getHeadingRadians()));
        Position2DAngle relativeValues = Robot.DCGm.relativeValues(new Position2DAngle(drivex,drivey,turn), Heading);
        Position2DAngle adjustedValues = new Position2DAngle( relativeValues.X * PERCENTAGE
                                                            , relativeValues.Y * PERCENTAGE
                                                            , relativeValues.ANGLE);
        Robot.DCGm.driveToPositionAngle(adjustedValues, true);

        //Buttons
        //DPad_UP

        //DPad_RIGHT

        //DPad_D


        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}
