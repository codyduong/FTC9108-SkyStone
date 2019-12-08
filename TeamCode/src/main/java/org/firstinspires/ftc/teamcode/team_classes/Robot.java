package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;
import org.openftc.revextensions2.ExpansionHubEx;

public class Robot {
    public Position2D Position;
    public Position2D EstimatedPosition;
    public double Angle;

    public DriverConfiguration Driver1; //these DriverConfigurations use circular references which hurt my brain.
    public DriverConfiguration Driver2;
    public Mecanum DCGm;
    public ColorSensorGroup CSG;
    public BNOIMU IMU;
    public ServoGroup SG;
    public Lift DCGl;
    public Intake DCGi;
    public RevHubGroup RHG;

    //Constructor (hardware maps everything)
    public Robot(Gamepad gamepad1, Gamepad gamepad2) {
        Driver1 = new DriverConfiguration(this, gamepad1);
        Driver2 = new DriverConfiguration(this, gamepad2);
        DCGm = new Mecanum();
        CSG = new ColorSensorGroup(new ColorSensor[2]);
        IMU = new BNOIMU(null);
        SG = new ServoGroup(new Servo[1]);
        DCGl = new Lift();
        DCGi = new Intake();
        RHG = new RevHubGroup(new ExpansionHubEx[2]);
    }

    public void initialize(HardwareMap HM, Telemetry T) {
        DCGm.initialize(HM, T);
        CSG.initialize(HM, T);
        IMU.initialize(HM, T);
        SG.initialize(HM, T);
        DCGl.initialize(HM, T);
        DCGi.initialize(HM, T);
        RHG.initialize(HM, T);
        T.addData("Robot Initialization","Complete");
    }

    private void updateRobot() {
        this.Angle = Math.toDegrees(IMU.getHeadingRadians());
    }

    /**
     * A method that runs all the essential processes of the robot. Including drivetrain, servos, etc...
     */
    public void run() {
        updateRobot();
        DCGm.teleOpDrive(new Position2DAngle((Driver1.drivex()),(Driver1.drivey()),(Driver1.turn())),Angle);
    }
}
