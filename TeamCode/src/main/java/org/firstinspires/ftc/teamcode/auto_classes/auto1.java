package org.firstinspires.ftc.teamcode.auto_classes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.team_methods.TeamMethods;

@TeleOp(name="test2", group="test") //fix this
public class auto1 extends TeamMethods {
    private ElapsedTime runtime = new ElapsedTime();

    //these values should be determined based off hardware being used
    /*
    private static final double ticksPerRev = 1;
    private static final double gearRatio = 1;
    private static final double wheelDiameter = 1;
    private static final double ticksPerInch = (ticksPerRev * gearRatio) / (wheelDiameter * 3.1415); //the math changes on mecanum. fix and find formula.
    */

    //Initialized by: Initialization Button (i think)
    public void init() {
        telemetry.addData("Status", "Initializing");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motor1 = hardwareMap.get(DcMotor.class, "left_drive_front");
        motor2 = hardwareMap.get(DcMotor.class, "left_drive_back");
        motor3 = hardwareMap.get(DcMotor.class, "right_drive_front");
        motor4 = hardwareMap.get(DcMotor.class, "right_drive_back");
        robotGyro = hardwareMap.get(GyroSensor.class,"gyrosensor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        motor1.setDirection(DcMotor.Direction.FORWARD);
        motor2.setDirection(DcMotor.Direction.FORWARD);
        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Encoders and GyroSensor","Resetting");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotGyro.calibrate();

        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    int position = 1;
    //Position 1 is building zone, and position 2 is loading zone.
    int offset;
    int averageColorFront;
    int avarageColorBottom;


    //Initialized by: Start / runs once
    @Override
    public void start() {
        offset = 0;
        avarageColorBottom = 0;
        averageColorFront = 0;
        ColorSensor bottomOds = new ColorSensor();
        ColorSensor frontOds = new ColorSensor();
        runtime.reset();

        if (position == 1) {
            //starting facing wall, and 42.25'' away from the other wall

            driveToPosition(0, -65.75, 0);
            driveToPosition(20, 0, 0);
            driveToPosition(0, 47.5, 0);
            driveToPosition(0, -47.5, 0);
            driveToPosition(-46.75, 0, 0);
            driveToPosition(0, -25, 0);
            while (frontOds.argb() < averageColorFront)
            {
                driveToPosition(-1, 0, 0);
                offset++;
            }

            // at this point, the robot will pick up the skystone

            driveToPosition(offset,0,0);
            driveToPosition(0,-43,0);
            driveToPosition(26.75,0,0);
            driveToPosition(0,68,0);
            driveToPosition(20,0,0);
            driveToPosition(0,0,180);

            //At this point, the robot will place the stone in the foundation.

            driveToPosition(0,0,180);
            driveToPosition(-20,0,0);
            driveToPosition(0,-68,0);
            while(bottomOds.argb() < avarageColorBottom)
            {
                driveToPosition(-1,0,0);
            }

            position = 3;
        }
        if (position == 2) {
            //Starting at wall, facing away, and 50" away from other wall.
            driveToPosition(0,29,0);
            while (frontOds.argb() < averageColorFront)
            {
                driveToPosition(-1, 0, 0);
                offset++;
            }

            //At this point, the robot will pick up the skystone.

            driveToPosition(offset,0,0);
            driveToPosition(0,-43,0);
            driveToPosition(26.75,0,0);
            driveToPosition(0,68,0);
            driveToPosition(20,0,0);
            driveToPosition(0,0,180);

            //At this point, the robot will place the stone in the foundation.

            driveToPosition(0,0,180);
            driveToPosition(-20,0,0);
            driveToPosition(0,-68,0);
            while(bottomOds.argb() < avarageColorBottom)
            {
                driveToPosition(-1,0,0);
            }
            
            position = 3;
        }

    }


    //Initialized by: After Start, Before Stop / loops
    @Override
    public void loop() {
    }

    //Initialized by: Stop / runs once
    @Override
    public void stop() {}

}