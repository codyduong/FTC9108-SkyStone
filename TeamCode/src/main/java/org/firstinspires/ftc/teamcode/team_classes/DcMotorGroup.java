package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.general_classes.Position2D;
import org.firstinspires.ftc.teamcode.general_classes.Position2DAngle;

public class DcMotorGroup {
    //Properties
    public DcMotor[] DcMotors;
    public int DcMotorCount;

    //Constructor
    public DcMotorGroup(DcMotor[] DcMotorArray) {
        DcMotors = DcMotorArray;
        DcMotorCount = DcMotorArray.length;
    }

    /**
     * A method that sets all the motors in the DcMotorGroup to a power array.
     * @param power an array of values, assigned in the order DcMotorGroup is assigned.
     */
    public void setPower(double[] power) {
        for(int i=0; i<DcMotorCount; i++) {
            this.DcMotors[i].setPower(power[i]);
        }
    }
}
