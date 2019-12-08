package org.firstinspires.ftc.teamcode.team_classes;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.team_classes.GamepadInput.getAnalogLength;
import static org.firstinspires.ftc.teamcode.team_classes.GamepadInput.getBinaryLength;

/**
 * When I look back on this, I will have no idea how I did it.
 * @author Cody Duong
 */
public class DriverConfiguration {
    private Robot Robot;
    private Gamepad GP;                     //gamepad reference
    private GamepadInput Config;
    private GamepadInput.Analog[] AConfig;  //analog config
    private SIGN[] ASConfig;                //analog_signage config
    private GamepadInput.Binary[] BConfig;  //binary config
    private ButtonMode[] BMConfig;          //button_mode config
    private GamepadInput.Analog[] Analog_Actions;
    private GamepadInput.Binary[] Binary_Actions;

    /**
     * @param HardwareRobot The robot class associated with all the hardware
     * @param Gamepad Which Gamepad is being associated with this configuration
     */
    public DriverConfiguration(Robot HardwareRobot, Gamepad Gamepad) {
        Robot = HardwareRobot;
        GP = Gamepad;
        Config = new GamepadInput();
        AConfig = new GamepadInput.Analog[getAnalogLength()];
        ASConfig = new SIGN[getAnalogLength()];
        BConfig = new GamepadInput.Binary[getBinaryLength()];
        BMConfig = new ButtonMode[getBinaryLength()];
        Analog_Actions = new GamepadInput.Analog[ActionAnalog.values().length];
        Binary_Actions = new GamepadInput.Binary[ActionBinary.values().length];
        lastRetDeb = 0;
        debounce = 0;
    }




    /** {**/
    //START ENUM STUFFS
    public enum ActionAnalog {
        drivex,
        drivey,
        turn,
    }
    private ActionAnalog[] A_Compare = ActionAnalog.values();
    private int getAANumber(ActionAnalog input) {
        for (int i=0; i<A_Compare.length; i++) {
            if (input == A_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid AA Check");
    }
    private ActionAnalog getAAInverseNumber(int input) {
        for (int i=0; i<A_Compare.length; i++) {
            if (A_Compare[input] == A_Compare[i]) {
                return A_Compare[i];
            }
        }
        throw new IllegalArgumentException("Invalid AAinv Check");
    }

    /****/

    public enum ActionBinary {
        swapDriveMode,
    }
    private ActionBinary[] B_Compare = ActionBinary.values();
    private int getABNumber(ActionBinary input) {
        for (int i=0; i<B_Compare.length; i++) {
            if (input == B_Compare[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid AB Check");
    }
    private ActionBinary getABInverseNumber(int input) {
        for (int i=0; i<B_Compare.length; i++) {
            if (B_Compare[input] == B_Compare[i]) {
                return B_Compare[i];
            }
        }
        throw new IllegalArgumentException("Invalid ABinv Check");
    }




    /**     {**/
    //Analog Actions
    public float drivex() {
        if (Analog_Actions[1] != null) {
            return retrieveAnalog(Analog_Actions[0]);
        } else {
            throw new NullPointerException("No control assigned to drivex");
        }
    }
    public float drivey() {
        if (Analog_Actions[1] != null) {
            return retrieveAnalog(Analog_Actions[1]);
        } else {
            throw new NullPointerException("No control assigned to drivey");
        }
    }
    public float turn() {
        if (Analog_Actions[1] != null) {
            return retrieveAnalog(Analog_Actions[2]);
        } else {
            throw new NullPointerException("No control assigned to turn");
        }
    }
    /**     }**/




    /**     {**/
    //Binary Actions

    /**     }**/




    /** }**/




    /**
     * Assigns a debounce time. If the Gamepad was last used in a certain time period,
     * especially for buttons, it will not rebounce, instead debouncing.
     * @param time A time unit (of unknown unit)
     */
    public void assignDebounce(int time) { debounce = time; }




    /**
     * Assigns an analog control to a robot action. Depending the robot action will see how this is activated.
     * @param AnalogInput Analog Gamepad Trigger/Joystick.
     * @param RobotAction A robot action.
     */
    public void assignAnalog(GamepadInput.Analog AnalogInput, ActionAnalog RobotAction) {
        AConfig[Config.getAnalogNumber(AnalogInput)] = AnalogInput;
        Analog_Actions[getAANumber(RobotAction)] = AnalogInput;
    }

    /**
     * Assigns a positive or negative association with an analog button.
     * @param AnalogInput Analog Gamepad Trigger/Joystick.
     * @param sign Either positive/negative SIGN.
     */
    public void assignSign(GamepadInput.Analog AnalogInput, SIGN sign) {
        ASConfig[Config.getAnalogNumber(AnalogInput)] = sign;
    }

    /**
     * Retrieves the values of a button.
     * @param AnalogInput Analog Gamepad Trigger/Joystick.
     * @return float
     */
    public float retrieveAnalog(GamepadInput.Analog AnalogInput) {
        if (ASConfig[Config.getAnalogNumber(AnalogInput)] == SIGN.POSITIVE) {
            return (Config.checkAnalog(GP, AnalogInput));
        } else {
            return (-1 * Config.checkAnalog(GP, AnalogInput));
        }
    }




    /**
     * Assigns an binary control to a robot action. Depending the robot action will see how this is activated.
     * @param BinaryInput Binary Gamepad Trigger/Joystick.
     * @param RobotAction A robot action.
     */
    public void assignBinary(GamepadInput.Binary BinaryInput, ActionBinary RobotAction) {
        BConfig[Config.getBinaryNumber(BinaryInput)] = BinaryInput;
        Binary_Actions[getABNumber(RobotAction)] = BinaryInput;
    }

    public void assignToggle(GamepadInput.Binary BinaryInput, ButtonMode ModeInput) {
        BMConfig[Config.getBinaryNumber(BinaryInput)] = ModeInput;
    }

    /**
     * Retrieves the values of a button.
     * @param BinaryInput Binary Gamepad Trigger/Joystick.
     * @return boolean
     */
    private int lastRetDeb;
    private int debounce; //measured in an unknown (if even consistent) unit? (gamepad uses bytebuffer and im not smart enough for that)
    public boolean retrieveBinary(GamepadInput.Binary BinaryInput) {
        if (GP.timestamp > lastRetDeb) {
            return (Config.checkBinary(GP, BinaryInput));
        } else {
            return false;
        }
    }




    /**
     * Composes some fun facts.
     */
    public void composeTelemetry() {

    }
}
