package org.firstinspires.ftc.teamcode.team_classes.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.team_classes.robot.DcMotorGroup;

public class Intake extends DcMotorGroup {

    public Intake() { super(new DcMotor[1]); }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.DcMotors[0] = Hmap.get(DcMotor.class, "Intake_motor");
        Tm.addData("Intake Initialization","Complete");
    }


}