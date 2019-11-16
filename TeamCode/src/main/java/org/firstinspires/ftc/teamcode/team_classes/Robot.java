package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public DcMotorGroup DCG;
    public ColorSensorGroup CSG;
    public BNOIMU IMU;

    public void initialize(HardwareMap HM, Telemetry T) {
        DCG.initialize(HM, T);
        CSG.initialize(HM, T);
        IMU.initialize(HM, T);
    }
}
