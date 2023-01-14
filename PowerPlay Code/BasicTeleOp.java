

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic:Teleop mecanum", group="MechaumDriveTest")
public class PowerPlayTeleOp extends LinearOpMode{
    //declare OpMode Members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontWheel = null;
    private DcMotor rightFrontWheel = null;
    private DcMotor rightBackWheel = null;
    private DcMotor leftBackWheel = null;

    private DcMotor linearExtender = null;
    private CRServo claw = null;






    //private DcMotor UpGang = null;
    //private DcMotor DownGang = null;
    //private DcMotor upDown1 = null;
    ///private DcMotor upDown2 = null;


    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");

        claw = hardwareMap.get(CRServo.class, "claw");
        linearExtender = hardwareMap.get(DcMotor.class, "LinearExtender");








        //Hint: declare direction of motor
        leftFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotor.Direction.REVERSE);
        rightBackWheel.setDirection(DcMotor.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotor.Direction.FORWARD);
        linearExtender.setDirection(DcMotor.Direction.FORWARD);
        claw.setDirection(CRServo.Direction.FORWARD);




        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)


        waitForStart();

        runtime.reset();

        //Hint: Google the meaning of double in this context (if you don't already know)
        //Hint: Ch means channel, it's just the term used, doesn't signify anything

        double Ch1;
        double Ch2;
        double Ch3;
        double deadzone = 0;
        double linearPowerUp = 0;
        double linearPowerDown = 0;
        boolean zeroPos = false;
        boolean closedPos = false;


        final double CLOSED_VALUE = 0.2;
        final double OPEN_VALUE = 1;








        //Hint: until Driver presses STOP in the app
        //Hint: the number 1.75 is there for a reason
        //Hint: the gamepad is the thing used to control the robot during the driver controlled period
        //commented out "/1.75"
        while (opModeIsActive()) {



            //scale the inputs2
            Ch1 = -gamepad1.right_stick_x * 0.8;
            Ch2 = gamepad1.left_stick_y * 0.8;
            Ch3 = -gamepad1.left_stick_x * 0.8;
            linearPowerUp = gamepad1.right_trigger * 0.5;
            linearPowerDown = -gamepad1.left_trigger * 0.5;
            zeroPos = gamepad1.x;
            closedPos = gamepad1.y;


            if(linearPowerUp != 0 && linearPowerDown == 0){
                linearExtender.setPower(linearPowerUp);
            }

            if (linearPowerDown != 0 && linearPowerUp == 0){
                linearExtender.setPower(linearPowerDown);
            }

            if(gamepad1.x && !gamepad1.y){
                claw.setPower(0.5);
            }
            if(gamepad1.y && !gamepad1.x){
                claw.setPower(-0.5);
            }
            else{
                claw.setPower(0);
            }

            /*
            if(zeroPos && !closedPos){
                claw.setPosition(OPEN_VALUE);
            }

            if(closedPos && !zeroPos){
                claw.setPosition(CLOSED_VALUE);
            }
            */




            if(gamepad1.back){
                leftFrontWheel.setPower(0);
                leftBackWheel.setPower(0);
                rightBackWheel.setPower(0);
                rightFrontWheel.setPower(0);
            }

            //Hint:Send calculated power to wheels
            leftFrontWheel.setPower(Ch3 + Ch1 + Ch2);
            leftBackWheel.setPower(Ch1 + Ch2 - Ch3);
            rightBackWheel.setPower(Ch2 - Ch1 + Ch3);
            rightFrontWheel.setPower(Ch2 - Ch1 - Ch3);





        }


    }
}
