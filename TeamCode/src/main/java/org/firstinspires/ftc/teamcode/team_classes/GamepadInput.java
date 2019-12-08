package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Basically this is a class with some neat methods and enums to handle Gamepad Inputs.
 * I'll maybe or maybe not add more documentation...
 * Reference JavaDoc used: https://first-tech-challenge.github.io/SkyStone/doc/javadoc/index.html
 * @author Cody Duong
 */
public class GamepadInput {
    public enum Analog {
        left_stick_x,
        left_stick_y,
        left_trigger,

        right_stick_x,
        right_stick_y,
        right_trigger,
    }

    public static int getAnalogLength() {return Analog.values().length;}
    private Analog[] getAnalog() {return Analog.values();}
    public float checkAnalog(Gamepad Gamepad, Analog Button) {
        if(Button == Analog.left_stick_x) {
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

    //probably a bad way to this but whatever.
    private Analog[] A_Compare = getAnalog();
    public int getAnalogNumber(Analog Button) {
        for (int i=0; i<A_Compare.length; i++) {
            if (Button == A_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Analog Check");
    }

    public enum Binary {
        a,
        b,
        x,
        y,

        dpad_down,
        dpad_left,
        dpad_right,
        dpad_up,

        left_bumper,
        left_stick_button,

        right_bumper,
        right_stick_button,

        back,
        guide,
        start,
    }
    public static int getBinaryLength() {return Binary.values().length;}
    private Binary[] getBinary() {return Binary.values();}
    public boolean checkBinary(Gamepad Gamepad, Binary Button) {
        if(Button == Binary.a) {
            return Gamepad.a;
        } else if (Button == Binary.b) {
            return Gamepad.b;
        } else if (Button == Binary.x) {
            return Gamepad.x;
        } else if (Button == Binary.y) {
            return Gamepad.y;
        } else if (Button == Binary.dpad_down) {
            return Gamepad.dpad_down;
        } else if (Button == Binary.dpad_left) {
            return Gamepad.dpad_left;
        } else if (Button == Binary.dpad_right) {
            return Gamepad.dpad_right;
        } else if (Button == Binary.dpad_up) {
            return Gamepad.dpad_up;
        } else if (Button == Binary.left_bumper) {
            return Gamepad.left_bumper;
        } else if (Button == Binary.left_stick_button) {
            return Gamepad.left_stick_button;
        } else if (Button == Binary.right_bumper) {
            return Gamepad.right_bumper;
        } else if (Button == Binary.right_stick_button) {
            return Gamepad.right_stick_button;
        } else if (Button == Binary.back) {
            return Gamepad.back;
        } else if (Button == Binary.start) {
            return Gamepad.start;
        } else if (Button == Binary.guide) {
            return Gamepad.guide;
        } else {
            throw new IllegalArgumentException("Invalid Binary Button");
        }
    }

    private Binary[] B_Compare = getBinary();
    public int getBinaryNumber(Binary Button) {
        for (int i=0; i<B_Compare.length; i++) {
            if (Button == B_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Binary Check");
    }
}
