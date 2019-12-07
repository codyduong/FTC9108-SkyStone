package org.firstinspires.ftc.teamcode.team_classes;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Intake extends ServoGroup {


    public Intake() { super(new Servo[1]); }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.Servos[0] = Hmap.get(Servo.class, "Intake_servo");
    }

    public void grab(){
        this.Servos[0].setPosition(1);
    }

    public void drop(){
        this.Servos[0].setPosition(0);
    }

    public void rotateToAngle(double angleTheta){
        double possition = angleTheta / 180;
        this.Servos[0].setPosition(possition);
    }

}