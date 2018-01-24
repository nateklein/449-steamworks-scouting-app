package robo.scoutingappmockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class goToTeleop extends Activity {

    // Data trackers
    private int gears;
    private int highFuel;
    private int lowFuel;

    // Text fields that display current value
    private TextView gearText;
    private TextView highFuelText;
    private TextView lowFuelText;

    // Input fields
    private RadioGroup dead;
    private CheckBox achievedNothingBox;

    // Displays teleop page on activity call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teleop_page);
        // Creates and sets data trackers to their SteamworksDatabase values
        gears = MainActivity.db.teleopGears;
        highFuel = MainActivity.db.teleopHighFuel;
        lowFuel = MainActivity.db.teleopLowFuel;
        gearText = (TextView) findViewById(R.id.teleopGears);
        gearText.setText(Integer.toString(gears));
        highFuelText = (TextView) findViewById(R.id.teleopHighFuel);
        highFuelText.setText(Integer.toString(highFuel));
        lowFuelText = (TextView) findViewById(R.id.teleopLowFuel);
        lowFuelText.setText(Integer.toString(lowFuel));
        dead = (RadioGroup) findViewById(R.id.dead);
        switch (MainActivity.db.dead) {
            case 0:
                dead.clearCheck();
                break;
            case 1:
                dead.check(R.id.deadNone);
                break;
            case 2:
                dead.check(R.id.deadPart);
                break;
            case 3:
                dead.check(R.id.deadAll);
                break;
        }
        achievedNothingBox = (CheckBox) findViewById(R.id.achievedNothing);
        achievedNothingBox.setChecked(MainActivity.db.achievedNothing);
    }

    // Gear adding and subtracting
    public void increaseGears(View v) {
        gears++;
        gearText.setText(Integer.toString(gears));
    }

    public void decreaseGears(View v) {
        if (gears > 0) {
            gears--;
            gearText.setText(Integer.toString(gears));
        }
    }

    // Fuel adding and subtracting
    public void highFuelPlus1(View v) {
        highFuel++;
        highFuelText.setText(Integer.toString(highFuel));
    }

    public void highFuelPlus5(View v) {
        highFuel+=5;
        highFuelText.setText(Integer.toString(highFuel));
    }

    public void highFuelMinus1(View v) {
        if (highFuel > 0) {
            highFuel--;
            highFuelText.setText(Integer.toString(highFuel));
        }
    }

    public void highFuelMinus5(View v) {
        if (highFuel > 4) {
            highFuel-=5;
            highFuelText.setText(Integer.toString(highFuel));
        }
    }

    public void lowFuelPlus1(View v) {
        lowFuel++;
        lowFuelText.setText(Integer.toString(lowFuel));
    }

    public void lowFuelPlus5(View v) {
        lowFuel+=5;
        lowFuelText.setText(Integer.toString(lowFuel));
    }

    public void lowFuelMinus1(View v) {
        if (lowFuel > 0) {
            lowFuel--;
            lowFuelText.setText(Integer.toString(lowFuel));
        }
    }

    public void lowFuelMinus5(View v) {
        if (lowFuel > 4) {
            lowFuel-=5;
            lowFuelText.setText(Integer.toString(lowFuel));
        }
    }

    // Calls activity to go to auto page
    public void toAuto(View v) {
        // Saves values to SteamworksDatabase
        MainActivity.db.teleopGears = gears;
        MainActivity.db.teleopLowFuel = lowFuel;
        MainActivity.db.teleopHighFuel = highFuel;
        switch (dead.getCheckedRadioButtonId()) {
            case R.id.deadNone:
                MainActivity.db.dead = 1;
                break;
            case R.id.deadPart:
                MainActivity.db.dead = 2;
                break;
            case R.id.deadAll:
                MainActivity.db.dead = 3;
                break;
            default:
                MainActivity.db.dead = 0;
                break;
        }
        MainActivity.db.achievedNothing = achievedNothingBox.isChecked();
        // Switches pages
        Intent toAuto = new Intent(this, goToAuto.class);
        startActivity(toAuto);
    }

    // Calls activity to go to endgame page
    public void toEndgame(View v) {
        // Saves values to SteamworksDatabase
        MainActivity.db.teleopGears = gears;
        MainActivity.db.teleopLowFuel = lowFuel;
        MainActivity.db.teleopHighFuel = highFuel;
        switch (dead.getCheckedRadioButtonId()) {
            case R.id.deadNone:
                MainActivity.db.dead = 1;
                break;
            case R.id.deadPart:
                MainActivity.db.dead = 2;
                break;
            case R.id.deadAll:
                MainActivity.db.dead = 3;
                break;
            default:
                MainActivity.db.dead = 0;
                break;
        }
        MainActivity.db.achievedNothing = achievedNothingBox.isChecked();
        // Switches pages
        Intent toEndgame = new Intent(this, goToEndgame.class);
        startActivity(toEndgame);
    }
}