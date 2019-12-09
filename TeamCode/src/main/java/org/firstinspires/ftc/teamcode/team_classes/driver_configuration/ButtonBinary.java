package org.firstinspires.ftc.teamcode.team_classes.driver_configuration;

import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonSimple.TYPE.BINARY;

public class ButtonBinary extends ButtonSimple {
    public Binary Button;
    public ACTUATE Actuate;
    public Action.Binary_Action Action;
    public boolean Toggled;

    public ButtonBinary() {
        super(BINARY);
        Button = Binary.none;
        Actuate = ACTUATE.UNTOGGLE;
        Toggled = false;
    }

    public enum Binary {
        none,

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

    public enum ACTUATE {
        TOGGLE,
        UNTOGGLE,
    }

    public static int getBinaryLength() {return Binary.values().length;}
    private static Binary[] getBinary() {return Binary.values();}
    public static boolean checkBinary(Gamepad Gamepad, Binary Button) {
        if(Button == Binary.none) {
            return false;
        } else if(Button == Binary.a) {
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

    public static int getBinaryNumber(Binary Binary_Button) {
        Binary[] B_Compare = getBinary();
        for (int i=0; i<B_Compare.length; i++) {
            if (Binary_Button == B_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Binary Check");
    }
}
