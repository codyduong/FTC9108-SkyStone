package org.firstinspires.ftc.teamcode.team_classes.driver_configuration;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.team_classes.robot.Robot;

import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonAnalog.getAnalogLength;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary.getBinaryLength;
import static org.firstinspires.ftc.teamcode.team_classes.driver_configuration.ButtonBinary.getBinaryNumber;

/**
 * When I look back on this, I will have no idea how I did it.
 * @author Cody Duong
 */
public class DriverConfiguration {
    private org.firstinspires.ftc.teamcode.team_classes.robot.Robot Robot;
    public ButtonAnalog[] Analogs;
    public ButtonBinary[] Binarys;
    private Gamepad GP;     //gamepad reference
    private int lastRetDeb;
    private int debounce; //measured in an unknown (if even consistent) unit? (gamepad uses bytebuffer and im not smart enough for that)

    /**
     * @param HardwareRobot The robot class associated with all the hardware
     * @param Gamepad Which Gamepad is being associated with this configuration
     */
    public DriverConfiguration(Robot HardwareRobot, Gamepad Gamepad) {
        Robot = HardwareRobot;
        GP = Gamepad;
        Analogs = new ButtonAnalog[getAnalogLength()];
        Binarys = new ButtonBinary[getBinaryLength()];
        lastRetDeb = 0;
        debounce = 0;
    }




    /**
     * Assigns a debounce time. If the Gamepad was last used in a certain time period,
     * especially for buttons, it will not rebounce, instead debouncing.
     * @param time A time unit (of unknown unit)
     */
    public void assignDebounce(int time) { debounce = time; }




    /**
     * Assigns an analog control to a robot action. Depending the robot action will see how this is activated.
     * @param ABS_Button Analog Gamepad Trigger/Joystick.
     * @param RobotAction A robot action.
     */
    public void assignAnalog(ButtonAnalog.Analog ABS_Button, Action.Analog_Action RobotAction) {
        Analogs[ButtonAnalog.getAnalogNumber(ABS_Button)].Button = ABS_Button;
        Analogs[ButtonAnalog.getAnalogNumber(ABS_Button)].Action = RobotAction;
    }

    /**
     * Assigns a positive or negative association with an analog button.
     * @param ABS_Button Analog Gamepad Trigger/Joystick.
     * @param Sign Either positive/negative SIGN.
     */
    public void assignSign(ButtonAnalog.Analog ABS_Button, ButtonAnalog.SIGN Sign) {
        Analogs[ButtonAnalog.getAnalogNumber(ABS_Button)].Sign = Sign;
    }

    /**
     * Retrieves the values of a button object.
     * @param ABS_Button Analog Gamepad Trigger/Joystick.
     * @return float
     */
    public float retrieveAnalog(ButtonAnalog.Analog ABS_Button) {
        if (Analogs[ButtonAnalog.getAnalogNumber(ABS_Button)].Sign == ButtonAnalog.SIGN.POSITIVE) {
            return (ButtonAnalog.checkAnalog(GP, ABS_Button));
        } else {
            return (-1 * ButtonAnalog.checkAnalog(GP, ABS_Button));
        }
    }

    /**
     * Retrieves the values of a button object from an action
     * @param ABS_Action Action
     * @return float
     */
    public float retrieveAnalogFromAction(Action.Analog_Action ABS_Action) {
        for (int i=0; i<Analogs.length; i++) {
            if (Analogs[i].Action == ABS_Action) {
                return retrieveAnalog(Analogs[i].Button);
            }
        }
        throw new IllegalArgumentException("No button is assigned to that action");
    }




    /**
     * Assigns an binary control to a robot action. Depending the robot action will see how this is activated.
     * @param ABS_Button Binary Gamepad Button
     * @param RobotAction A robot action.
     */
    public void assignBinary(ButtonBinary.Binary ABS_Button, Action.Binary_Action RobotAction) {
        Binarys[ButtonBinary.getBinaryNumber(ABS_Button)].Button = ABS_Button;
        Binarys[ButtonBinary.getBinaryNumber(ABS_Button)].Action = RobotAction;
    }

    /**
     * Assigns the toggleability of the button
     * @param ABS_Button Binary Gamepad Button
     * @param ModeInput Type
     */
    public void assignToggle(ButtonBinary.Binary ABS_Button, ButtonBinary.ACTUATE ModeInput) {
        Binarys[ButtonBinary.getBinaryNumber(ABS_Button)].Actuate = ModeInput;
    }

    /**
     * Retrieves the values of a button.
     * @param ABS_Button Binary Gamepad Trigger/Joystick.
     * @return boolean
     */
    public boolean retrieveBinary(ButtonBinary.Binary ABS_Button) {
        if (Binarys[getBinaryNumber(ABS_Button)].Actuate == ButtonBinary.ACTUATE.TOGGLE) {
            if (GP.timestamp > lastRetDeb) {
                Binarys[getBinaryNumber(ABS_Button)].Toggled = !Binarys[getBinaryNumber(ABS_Button)].Toggled;
            }
            return Binarys[getBinaryNumber(ABS_Button)].Toggled;
        } else {
            if (GP.timestamp > lastRetDeb) {
                return (ButtonBinary.checkBinary(GP, ABS_Button));
            }
            return false;
        }
    }

    /**
     * Retrieves the values of a button object from an action
     * @param ABS_Action Action
     * @return float
     */
    public boolean retrieveBinaryFromAction(Action.Binary_Action ABS_Action) {
        for (int i=0; i<Binarys.length; i++) {
            if (Binarys[i].Action == ABS_Action) {
                return retrieveBinary(Binarys[i].Button);
            }
        }
        throw new IllegalArgumentException("No button is assigned to that action");
    }
}
