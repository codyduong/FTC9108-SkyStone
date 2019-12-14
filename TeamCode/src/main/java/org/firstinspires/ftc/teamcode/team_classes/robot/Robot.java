package org.firstinspires.ftc.teamcode.team_classes.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary;
import org.firstinspires.ftc.teamcode.team_classes.driver_configuration.DriverConfiguration;
import org.openftc.revextensions2.ExpansionHubEx;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Analog_Action.*;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.Action.Binary_Action.*;

public class Robot {
    public Position2D Position;
    public Position2D EstimatedPosition;
    public double Angle;

    private Telemetry tmtr;
    private HardwareMap hMap;

    public DriverConfiguration Driver1; //these DriverConfigurations use circular references which hurt my brain.
    public DriverConfiguration Driver2;
    public Mecanum DCGm;
    public ColorSensorGroup CSG;
    public BNOIMU IMU;
    public Lift DCGl;
    public Intake SGi;
    public RevHubGroup RHG;

    //Constructor (hardware maps everything)
    public Robot(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry, HardwareMap hardwareMap) {
        tmtr = telemetry;
        hMap = hardwareMap;
        Driver1 = new DriverConfiguration(this, gamepad1);
        Driver2 = new DriverConfiguration(this, gamepad2);
        DCGm = new Mecanum();
        CSG = new ColorSensorGroup(new ColorSensor[2]);
        IMU = new BNOIMU(null);
        DCGl = new Lift();
        SGi = new Intake();
        RHG = new RevHubGroup(new ExpansionHubEx[2]);
    }

    public void initialize() {
        tmtr.addData("Robot Initialization", "Start");
        DCGm.initialize(hMap, tmtr);
        CSG.initialize(hMap, tmtr);
        IMU.initialize(hMap, tmtr);
        DCGl.initialize(hMap, tmtr);
        SGi.initialize(hMap, tmtr);
        RHG.initialize(hMap, tmtr);
        tmtr.addData("Robot Initialization","Complete");
    }

    private void updateRobot() {
        this.Angle = Math.toDegrees(IMU.getHeadingRadians());
    }

    /**
     * A method that runs all the essential processes of the robot. Including drivetrain, servos, etc...
     */
    public void run() {
        updateRobot();
        robotMecanumDrive();
        swapDrive();
        /*
        faceUp();
        faceRight();
        faceDown();
        faceLeft();
         */
        //elevatorDrive();
        elevatorRaiseAbs();
        elevatorLowerAbs();
        //intakeGrab();
        //intakeDrop();
        turnLeft();
        turnRight();
        //resetGyro();
    }

    public void stop() {
        if (DCGm.isBusy()) {
            DCGm.setPower(new double[]{0,0,0,0});
        }
        if (DCGl.isBusy()) {
            DCGl.setPower(new double[]{0});
        }
    }

    private double drivex;
    private double drivey;
    private double turn;
    public void robotMecanumDrive() {
        drivex = (double)Driver1.retrieveAnalogFromAction(Action.Analog_Action.drivex);
        tmtr.addData("drivex", drivex);
        drivey = (double)Driver1.retrieveAnalogFromAction(Action.Analog_Action.drivey);
        tmtr.addData("drivey", drivey);
        turn = (double)Driver1.retrieveAnalogFromAction(Action.Analog_Action.turn);
        tmtr.addData("turn", turn);
        if (Math.abs(drivey) < .05) {
            drivey = 0;
        }
        if (Math.abs(drivex) < .05) {
            drivex = 0;
        }
        if (Math.abs(turn) < .05) {
            turn = 0;
        }
        Position2DAngle InputDrive = new Position2DAngle(drivex,drivey,turn);
        tmtr.addData("InputX", InputDrive.X);
        tmtr.addData("InputY", InputDrive.Y);
        tmtr.addData("InputTheta", InputDrive.ANGLE);
        if (DCGm.relativeDrive) {
            DCGm.driveToPositionAngle(DCGm.relativeValues(InputDrive, Angle), true, 1, 500);;
        }
        //DCGm.teleOpDrive(InputDrive, Angle, InputDrive.getMagnitude(), 5000);
    }

    public void swapDrive() {
        if (Driver1.retrieveBinaryFromAction(swapDriveMode)) {
            DCGm.setRelativeDriveToggle();
            if (DCGm.relativeDrive) {
                RHG.Hubs[0].setLedColor(0,255,255);
            } else {
                RHG.Hubs[0].setLedColor(100,0,255);
            }
        }
    }

    public void faceUp() {

    }

    public void faceRight() {
    }

    public void faceDown() {
    }

    public void faceLeft() {
    }

    public void turnLeft() {
        double speed;
        try {
            speed = Driver1.retrieveAnalogFromAction(ANALOG_turnLeft)*-90;
            Position2DAngle InputDrive = new Position2DAngle(0,0,speed);
            DCGm.teleOpDrive(InputDrive, Angle, speed, 500);
        } catch(IllegalArgumentException e1) {
            if (Driver1.retrieveBinaryFromAction(BINARY_turnLeft)) {
                speed = 90;
            } else {
                speed = 0;
            }
            Position2DAngle InputDrive = new Position2DAngle(0,0,speed);
            DCGm.teleOpDrive(InputDrive, Angle, speed, 500);
        }
    }

    public void turnRight() {
        double speed;
        try {
            speed = Driver1.retrieveAnalogFromAction(ANALOG_turnRight)*90;
            Position2DAngle InputDrive = new Position2DAngle(0,0,speed);
            DCGm.teleOpDrive(InputDrive, Angle, speed, 500);
        } catch(IllegalArgumentException e1) {
            if (Driver1.retrieveBinaryFromAction(BINARY_turnRight)) {
                speed = -90;
            } else {
                speed = 0;
            }
            Position2DAngle InputDrive = new Position2DAngle(0,0,speed);
            DCGm.teleOpDrive(InputDrive, Angle, speed, 500);
        }
    }

    public void resetGyro() {
        if (Driver1.retrieveBinaryFromAction(resetGyro)) {
            IMU.resetAngle();
            RHG.Hubs[0].setLedColor(255, 0, 0);
            if (DCGm.relativeDrive) {
                RHG.Hubs[0].setLedColor(0,255,255);
            } else {
                RHG.Hubs[0].setLedColor(100,0,255);
            }
        }
    }

    public void elevatorDrive() {
        double lifts = Driver2.retrieveAnalogFromAction(elevatorDrive);
        if (Math.abs(lifts) < 0.05) {
            lifts = 0;
        }
        DCGl.raiseToInch(0.01,lifts);
    }

    public void elevatorRaiseAbs() {
        if (Driver2.retrieveBinaryFromAction(elevatorRaiseAbs)) {
            DCGl.raiseToBlock(1,75);
        }
    }

    public void elevatorLowerAbs() {
        if (Driver2.retrieveBinaryFromAction(elevatorLowerAbs)) {
            DCGl.raiseToBlock(-1,75);
        }
    }

    public void intakeGrab() {
        if (Driver2.retrieveAnalogFromAction(intakeGrab) > 0.9) {
            //SGi.grab();
        }
    }

    public void intakeDrop() {
        if (Driver2.retrieveAnalogFromAction(intakeDrop) > 0.9) {
            //SGi.drop();
        }
    }
}
