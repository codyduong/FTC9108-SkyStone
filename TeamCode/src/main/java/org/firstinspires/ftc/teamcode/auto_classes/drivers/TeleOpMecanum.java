package org.firstinspires.ftc.teamcode.auto_classes.drivers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.Mecanum;
import org.firstinspires.ftc.teamcode.team_classes.Robot;


@TeleOp(name="Default Normal", group="9108") //fix this
public class TeleOpMecanum extends OpMode {

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
        telemetry.setAutoClear(true);
        Robot.DCGm.setRelativeDrive(true);
        Robot.RHG.Hubs[0].setLedColor(255,255,255);
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
        double Heading = (Math.toDegrees(Robot.IMU.getHeadingRadians()));
        Robot.DCGm.teleOpDrive(new Position2DAngle(drivex,drivey,turn),Heading);

        final int debounceTime = 500; //in ms
        double runtimeRef = 0;
        double runtimeRef2 = runtimeRef + debounceTime;
        if(gamepad1.a) {
            if (runtimeRef2 < runtime.milliseconds()) {
                Robot.DCGm.setRelativeDriveToggle();
                if (Robot.DCGm.relativeDrive) {
                    Robot.RHG.Hubs[0].setLedColor(255,255,255);
                } else {
                    Robot.RHG.Hubs[0].setLedColor(255,0,0);
                }
                runtimeRef = runtime.milliseconds();
            }
        }

        /*
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
        if (on == true) {
            Robot.DCGi.setPower(new double[]{1});
        }
        if (on == false) { 
            Robot.DCGi.setPower(new double[]{0});
        }

        if (gamepad2.left_trigger == 1) {
            on = true;
        }

        if (gamepad2.right_trigger == 1) {
            on = false;
        }
         */

        Robot.DCGm.composeTelemetry(telemetry);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.DCGm.setPower(new double[]{0,0,0,0});
    }
}
