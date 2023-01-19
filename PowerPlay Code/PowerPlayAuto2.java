import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "TeamBlue_RedSide")

public class PowerPlayAuto2 extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontWheel = null;
    private DcMotor rightFrontWheel = null;
    private DcMotor rightBackWheel = null;
    private DcMotor leftBackWheel = null;
    private DcMotor linearExtender = null;
    private Servo claw = null;

    enum Direction {FORWARD, BACKWARD, TURNLEFT, TURNRIGHT, RIGHTNOTRN, LEFTNOTRN} // RIGHTNOTRN and LEFTNOTRN are for omni, ignore if using regular wheels@#

    ;

    public void forwards(double pwr, double time) {

        runtime.reset();
        leftFrontWheel.setPower(pwr);
        rightFrontWheel.setPower(pwr);
        leftBackWheel.setPower(pwr);
        rightBackWheel.setPower(pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {
        }
        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);
    }// drive method

    public void backwards(double pwr, double time) {

        //drive
        runtime.reset();
        leftFrontWheel.setPower(-pwr);
        rightFrontWheel.setPower(-pwr);
        leftBackWheel.setPower(-pwr);
        rightBackWheel.setPower(-pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {
        }
        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);
    }// drive method

    public void rightturn(double degree) {
        double pwr = 0.4;

            double time = degree / 180.0;
            //drive
            runtime.reset();
            leftFrontWheel.setPower(pwr);
            rightFrontWheel.setPower(-pwr);
            leftBackWheel.setPower(pwr);
            rightBackWheel.setPower(-pwr);
            while (opModeIsActive() && (runtime.seconds() < time)) {

            }


            //stop
            leftFrontWheel.setPower(0.0);
            rightFrontWheel.setPower(0.0);
            leftBackWheel.setPower(0.0);
            rightBackWheel.setPower(0.0);

        } // turn method


    public void leftturn(double degree) {
        double pwr = -0.4;
        double time = degree / 180.0;
        //drive
        runtime.reset();
        leftFrontWheel.setPower(pwr);
        rightFrontWheel.setPower(pwr);
        leftBackWheel.setPower(pwr);
        rightBackWheel.setPower(pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }


        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);

    } // turn method

    public void rightstrafe(double pwr, double time) {

        runtime.reset();
        leftFrontWheel.setPower(pwr);
        rightFrontWheel.setPower(-pwr);
        leftBackWheel.setPower(-pwr);
        rightBackWheel.setPower(pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }


        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);

    }// drive method

    public void leftstrafe(double pwr, double time) {

        //drive
        runtime.reset();
        leftFrontWheel.setPower(-pwr);
        rightFrontWheel.setPower(pwr);
        leftBackWheel.setPower(pwr);
        rightBackWheel.setPower(-pwr);

        while (opModeIsActive() && (runtime.seconds() < time)) { }

        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);

    }// drive method



    public void claw(Direction dir, double position, double time) {
        if (dir == Direction.FORWARD) {
            position = -position;
        }
        runtime.reset();
        claw.setPosition(position);

        while (opModeIsActive() && (runtime.seconds() < time)) { }

        claw.setPosition(0);
    }

    public void upClaw(double pwr, double time) {

        runtime.reset();
        linearExtender.setPower(pwr);

        while (opModeIsActive() && (runtime.seconds() < time)) { }

        linearExtender.setPower(0.0);

    }


    public void runOpMode() {
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");
        claw = hardwareMap.get(Servo.class, "claw");
        linearExtender = hardwareMap.get(DcMotor.class, "LinearExtender");

        //Hint: declare direction of motor
        leftFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotor.Direction.REVERSE);
        rightBackWheel.setDirection(DcMotor.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotor.Direction.FORWARD);
        linearExtender.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        telemetry.addData("Initialization", "Complete");

        waitForStart();

        forwards(0.5,0.5);
        rightstrafe(0.5,0.5);

    }

}
