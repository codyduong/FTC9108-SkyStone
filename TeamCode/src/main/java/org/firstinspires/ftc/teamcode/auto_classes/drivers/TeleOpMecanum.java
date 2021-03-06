package org.firstinspires.ftc.teamcode.auto_classes.drivers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.DriverConfiguration;
import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.Analog.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary.Binary.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.*;

@TeleOp(name="Default Normal", group="9108") //fix this
public class TeleOpMecanum extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot Robot;

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        Robot = new Robot(gamepad1, gamepad2, telemetry, hardwareMap);
        Robot.initialize();
        /*STARTDRIVER1*/
        Robot.Driver1 = new DriverConfiguration(Robot, gamepad1);
        Robot.Driver1.assignDebounce(500);
        Robot.Driver1.assignAnalog(left_stick_x, drivex);
        Robot.Driver1.assignAnalog(left_stick_y, drivey);
        Robot.Driver1.assignAnalog(left_trigger, ANALOG_turnLeft);
        Robot.Driver1.assignAnalog(right_trigger, ANALOG_turnRight);
        Robot.Driver1.assignAnalog(right_stick_x, turn);
        Robot.Driver1.assignBinary(dpad_up, faceUp);
        Robot.Driver1.assignBinary(dpad_right, faceRight);
        Robot.Driver1.assignBinary(dpad_down, faceDown);
        Robot.Driver1.assignBinary(dpad_left, faceLeft);
        Robot.Driver1.assignBinary(start, swapDriveMode);
        Robot.Driver1.assignBinary(back, resetGyro);
        Robot.Driver1.assignToggle(back, ButtonBinary.ACTUATE.TOGGLE);
        /*STARTDRIVER2*/
        Robot.Driver2 = new DriverConfiguration(Robot, gamepad2);
        Robot.Driver2.assignDebounce(500);
        Robot.Driver2.assignAnalog(left_stick_y, elevatorDrive);
        Robot.Driver2.assignSign  (left_stick_y, ButtonAnalog.SIGN.NEGATIVE);
        Robot.Driver2.assignAnalog(left_trigger, intakeGrab);
        Robot.Driver2.assignAnalog(right_trigger, intakeDrop);
        Robot.Driver2.assignBinary(a, elevatorLowerAbs);
        Robot.Driver2.assignBinary(y, elevatorRaiseAbs);
        /*ENCONFIG*/
        Robot.DCGm.setRelativeDrive(false);
        Robot.RHG.Hubs[0].setLedColor(100,0,255);
        telemetry.addData("Status","Complete");
        telemetry.update();
    }

    //Initialized by: Start / runs once
    @Override
    public void start() {
        runtime.reset();
        telemetry.setAutoClear(true);
        telemetry.clearAll();
        Robot.IMU.composeTelemetry(telemetry);
    }

    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
        Robot.run(runtime);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("RobotAngle", Robot.Angle);
        Robot.DCGm.composeTelemetry(telemetry);
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {
        Robot.stop();
    }
}
