package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public Mecanum DCGm;
    //public ColorSensorGroup CSG;
    public BNOIMU IMU;
    public Gyro Gyro;
    public ServoGroup SG;

    //constructor
    public Robot() {
        DCGm = new Mecanum();
        //CSG = new ColorSensorGroup(new ColorSensor[2]);
        IMU = new BNOIMU(null);
        SG = new ServoGroup(new Servo[1]);
        //Gyro = new Gyro(null);
    }

    public void initialize(HardwareMap HM, Telemetry T) {
        DCGm.initialize(HM, T);
        //CSG.initialize(HM, T);
        IMU.initialize(HM, T);
        SG.initialize(HM, T);
        //Gyro.initialize(HM, T);
    }
}
