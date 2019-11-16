package org.firstinspires.ftc.teamcode.team_methods;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public DcMotorGroup DCG;

    public void initialize(HardwareMap HM, Telemetry T) {
        DCG.initialization(HM, T);
    }
}
