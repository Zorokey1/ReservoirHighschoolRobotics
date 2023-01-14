import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "leftoBLOX")

public class PowerPlayAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontWheel = null;
    private DcMotor rightFrontWheel = null;
    private DcMotor rightBackWheel = null;
    private DcMotor leftBackWheel = null;
    private DcMotor linearExtender = null;
    private Servo claw = null;

    enum Direction {FORWARD, BACKWARD, TURNLEFT, TURNRIGHT, RIGHTNOTRN, LEFTNOTRN} // RIGHTNOTRN and LEFTNOTRN are for omni, ignore if using regular wheels@#

    ;

    public void forwards(Direction dir, double pwr, double time) {
        if (dir == Direction.FORWARD) {
            pwr = -pwr;
        }
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
    }// drive method

    public void backwards(Direction dir, double pwr, double time) {
        if (dir == Direction.BACKWARD) {
            pwr = -pwr;
        }
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

    public void rightturn(Direction dir, double degree) {
        double pwr;
        if (dir == Direction.TURNRIGHT) {
            pwr = -0.4;
        } else {
            pwr = 0.4;

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
    }

    public void leftturn(Direction dir, double degree) {
        double pwr;
        if (dir == Direction.TURNLEFT) {
            pwr = -0.4;
        } else
            pwr = 0.4;

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

    public void rightstrafe(Direction dir, double pwr, double time) {
        if (dir == Direction.RIGHTNOTRN) {
            pwr = -pwr;
        }
        //drive
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

    public void leftstrafe(Direction dir, double pwr, double time) {
        if (dir == Direction.LEFTNOTRN) {
            pwr = -pwr;
        }
        //drive
        runtime.reset();
        leftFrontWheel.setPower(-pwr);
        rightFrontWheel.setPower(pwr);
        leftBackWheel.setPower(pwr);
        rightBackWheel.setPower(-pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }


        //stop
        leftFrontWheel.setPower(0.0);
        rightFrontWheel.setPower(0.0);
        leftBackWheel.setPower(0.0);
        rightBackWheel.setPower(0.0);

    }// drive method

    public void outtake(Direction dir, double pwr, double time) {
        if (dir == Direction.FORWARD) {
            pwr = -pwr;
        }
        //drive
        runtime.reset();
        //outtake.setPower(pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }

        //stop
        //outtake.setPower(0.0);

    }// drive method

    public void claw(Direction dir, double position, double time) {
        if (dir == Direction.FORWARD) {
            position = -position;
        }
        runtime.reset();
        claw.setPosition(position);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }
        claw.setPosition(0);
    }

    public void upClaw(Direction dir, double pwr, double time) {
        if (dir == Direction.FORWARD) {
            pwr = -pwr;
        }
        runtime.reset();
       // upClaw.setPower(pwr);
        while (opModeIsActive() && (runtime.seconds() < time)) {

        }
        //upClaw.setPower(0.0);

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

        upClaw(Direction.FORWARD, 0.5, 1);
        forwards(Direction.FORWARD, 0.5, 1);
        leftstrafe(Direction.LEFTNOTRN, 0.5, 0.5);
        outtake(Direction.FORWARD, 0.5, 1);
        leftturn(Direction.TURNLEFT, 30);
        forwards(Direction.FORWARD, 0.5, 4);
        upClaw(Direction.FORWARD, -0.5, 1);
        backwards(Direction.BACKWARD, 0.5, 2);
        upClaw(Direction.FORWARD, 0.5, 1);

    }

}
