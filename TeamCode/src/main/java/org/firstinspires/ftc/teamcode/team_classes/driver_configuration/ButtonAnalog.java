package org.firstinspires.ftc.teamcode.team_classes.driver_configuration;

import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonSimple.TYPE.ANALOG;

public class ButtonAnalog extends ButtonSimple{
    public Analog Button;
    public SIGN Sign;
    public Action.Analog_Action Action;

    public ButtonAnalog() {
        super(ANALOG);
        Button = Analog.none;
        Sign = SIGN.NEGATIVE;
    }

    public enum Analog {
        none,

        left_stick_x,
        left_stick_y,
        left_trigger,

        right_stick_x,
        right_stick_y,
        right_trigger,
    }

    public enum SIGN {
        POSITIVE,
        NEGATIVE,
    }

    public static int getAnalogLength() {return Analog.values().length;}
    private static Analog[] getAnalog() {return Analog.values();}
    public static float checkAnalog(Gamepad Gamepad, Analog Button) {
        if(Button == Analog.none) {
            return 0;
        } else if(Button == Analog.left_stick_x) {
            return Gamepad.left_stick_x;
        } else if(Button == Analog.left_stick_y) {
            return Gamepad.left_stick_y;
        } else if(Button == Analog.left_trigger) {
            return Gamepad.left_trigger;
        } else if(Button == Analog.right_stick_x) {
            return Gamepad.right_stick_x;
        } else if(Button == Analog.right_stick_y) {
            return Gamepad.right_stick_y;
        } else if(Button == Analog.right_trigger) {
            return Gamepad.right_trigger;
        } else {
            throw new IllegalArgumentException("Invalid Analog Button");
        }
    }

    public static int getAnalogNumber(Analog Analog_Button) {
        Analog[] A_Compare = getAnalog();
        for (int i=0; i<A_Compare.length; i++) {
            if (Analog_Button == A_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Analog Check");
    }
}
