package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensorGroup {

    //Properties
    public ColorSensor[] ColorSensors;
    public int ColorSensorCount;
    public ColorSensor BottomColorSensor;
    public ColorSensor FrontColorSensor;

    //Constructor
    ColorSensorGroup(ColorSensor[] ColorSensorArray) {
        ColorSensors = ColorSensorArray;
        ColorSensorCount = ColorSensors.length;
        BottomColorSensor = ColorSensorArray[0];
        FrontColorSensor = ColorSensorArray[1];
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        ColorSensors[0] = Hmap.colorSensor.get("bottom_color");
        ColorSensors[1] = Hmap.colorSensor.get("front_color");
        Tm.addData("DcMotorGroup Initialization","Complete");
        Tm.update();
    }
}
