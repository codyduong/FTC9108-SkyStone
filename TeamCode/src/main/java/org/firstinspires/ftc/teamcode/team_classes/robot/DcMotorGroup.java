package org.firstinspires.ftc.teamcode.team_classes.robot;

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
     * Sets the power level of the motors, expressed as a fraction of the maximum possible power
     * / speed supported according to the run mode in which the motor is operating.
     * @param power - the new power level of the motors, a double array
     * with values within the interval [-1.0, 1.0]
     */
    public void setPower(double[] power) {
        for(int i=0; i<DcMotorCount; i++) {
            this.DcMotors[i].setPower(power[i]);
        }
    }

    /**
     * Returns true if any motor is currently advancing or retreating to a target position.
     * @return the state of any motor, true or false.
     */
    public boolean isBusy() {
        for(int i=0; i<DcMotorCount; i++) {
            if(this.DcMotors[i].isBusy()) {
                return true;
            }
        }
        return false;
    }
}
