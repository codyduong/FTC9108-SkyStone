package org.firstinspires.ftc.teamcode.team_classes.driver_configuration;

public class ButtonSimple {
    public TYPE TYPE;

    ButtonSimple(TYPE Type) {
        TYPE = Type;
    }

    public enum TYPE {
        ANALOG,
        BINARY,
    }
}
