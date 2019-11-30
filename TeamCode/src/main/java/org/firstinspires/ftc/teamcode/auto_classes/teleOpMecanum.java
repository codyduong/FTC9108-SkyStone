package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.Mecanum;
import org.firstinspires.ftc.teamcode.team_classes.Robot;


@TeleOp(name="Mecanum", group="9108") //fix this
public class teleOpMecanum extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot = new Robot();

    //Initialized by: Initialization Button (i think)
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
        runtime.reset();
    }

    boolean on;

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        //DRIVING
        double drivey = gamepad1.left_stick_y;
        double drivex = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        if (Math.abs((double)drivey) < .05) {
            drivey = 0;
        }
        if (Math.abs((double)drivex) < .05) {
            drivex = 0;
        }
        if (Math.abs((double)turn) < .05) {
            turn = 0;
        }
        double AverageHeading = (Math.toDegrees(Robot.IMU.getHeadingRadians()))/*/2*/;
        Position2DAngle relativeValues = Robot.DCGm.relativeValues(new Position2DAngle(drivex,drivey,turn), AverageHeading);
        Robot.DCGm.driveToPositionAngle(relativeValues, true);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();

        //Lift
        double lift = gamepad2.left_stick_y;
        double speed = lift * 100;
        Robot.DCGl.raiseToInch(0.1,speed);




        if (gamepad2.a) {
            Robot.DCGl.raiseToBlock(-1,75);
        }
        if (gamepad2.y) {
            Robot.DCGl.raiseToBlock(1,75);
        }

        //Intake
        if(on = true){
            Robot.DCGi.setPower(new double[]{1});
        }
        if(on = false){
            Robot.DCGi.setPower(new double[]{0});
        }

        if (gamepad2.left_trigger == 1){
            on = true;
        }

        if (gamepad2.right_trigger == 1){
            on = false;
        }

    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}
