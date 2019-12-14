package org.firstinspires.ftc.teamcode.team_classes.robot;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

public class Intake extends ServoGroup {

    public Intake() {
        super(new Servo[2]);
    }

    public void initialize(HardwareMap Hmap, Telemetry Tm) {
        this.Servos[0] = Hmap.get(Servo.class, "Intake_servo");
        this.Servos[1] = Hmap.get(Servo.class, "Intake_Holder");
        this.Servos[0].setDirection(Servo.Direction.REVERSE);
        this.Servos[1].setDirection(Servo.Direction.REVERSE);
    }

    public void grab(){
        this.Servos[0].setPosition(.5);
        //this.Servos[1].setPosition(.1);

    }

    public void drop(){
        this.Servos[0].setPosition(0);
        /*
        this.Servos[1].setPosition(.2);
        try {
            sleep(500);
            this.Servos[1].setPosition(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
    }



    public void rotateToAngle(double angleTheta){
        double position = angleTheta / 180;
        this.Servos[0].setPosition(position);
    }
}