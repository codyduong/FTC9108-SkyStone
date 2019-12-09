package org.firstinspires.ftc.teamcode.team_classes.driver_configuration;

public class Action {

    public enum Analog_Action {
        drivex,
        drivey,
        turn,
        turnSpecial,
        elevatorDrive,
        intakeGrab,
        intakeDrop,
        ANALOG_turnLeft,
        ANALOG_turnRight,
    }

    public enum Binary_Action {
        swapDriveMode,
        faceUp,
        faceRight,
        faceDown,
        faceLeft,
        BINARY_turnLeft,
        BINARY_turnRight,
        resetGyro,
        elevatorRaiseAbs,
        elevatorLowerAbs,
    }
}
