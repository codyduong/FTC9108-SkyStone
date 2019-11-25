package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoGroup {

    //Properties
    public Servo[] Servos;
    public int ServoCount;
    public Servo ServoFront;

    //Constructor
    ServoGroup(Servo[] ServoArray) {
        Servos = ServoArray;
        ServoCount = Servos.length;
        ServoFront = Servos[0];
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        Servos[0] = Hmap.servo.get("servo");
        Servos[0].setDirection(Servo.Direction.FORWARD);
        Servos[0].scaleRange(Servos[0].MIN_POSITION, Servos[0].MAX_POSITION);
        Tm.addData("DcMotorGroup Initialization","Complete");
        Tm.update();
    }
}
