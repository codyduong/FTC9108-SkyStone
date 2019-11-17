package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Gyro {
    public GyroSensor Sensor;
    Gyro(GyroSensor GyroSensor){
        Sensor = GyroSensor;
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        Sensor = Hmap.get(GyroSensor.class, "gyro_sensor");
        Sensor.isCalibrating();
        while(Sensor.isCalibrating()){}
    }
}
