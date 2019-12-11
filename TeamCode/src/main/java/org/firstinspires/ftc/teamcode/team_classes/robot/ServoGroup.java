package org.firstinspires.ftc.teamcode.team_classes.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoGroup {
    //Properties
    public Servo[] Servos;
    public int ServoCount;


    //Constructor
    ServoGroup(Servo[] ServoArray) {
        Servos = ServoArray;
        ServoCount = Servos.length;
    }


}
