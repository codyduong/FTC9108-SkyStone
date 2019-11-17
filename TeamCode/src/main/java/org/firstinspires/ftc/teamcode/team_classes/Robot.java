package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public Mecanum DCGm;
    public ColorSensorGroup CSG;
    public BNOIMU IMU;
    public Gyro Gyro;

    //constructor
    public Robot() {
        DCGm = new Mecanum();
        CSG = new ColorSensorGroup(null);
        IMU = new BNOIMU(null);
        Gyro = new Gyro(null);
    }

    public void initialize(HardwareMap HM, Telemetry T) {
        DCGm.initialize(HM, T);
        CSG.initialize(HM, T);
        IMU.initialize(HM, T);
        Gyro.initialize(HM, T);
    }
}
