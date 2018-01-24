package robo.scoutingappmockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class goToAuto extends Activity {

    // Data trackers
    private int autoHighFuel;
    private int autoLowFuel;

    // Text fields that display current value
    private TextView autoHighFuelText;
    private TextView autoLowFuelText;

    // Input fields
    private CheckBox noAutoBox;
    private CheckBox movedForwardBox;
    private RadioGroup autoGear;

    // Displays auto page on activity call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autonomous_page);
        // Create and set data trackers to values from SteamworksDatabase
        autoHighFuel = MainActivity.db.autoHighFuel;
        autoLowFuel = MainActivity.db.autoLowFuel;
        autoHighFuelText = (TextView) findViewById(R.id.autoHighFuel);
        autoHighFuelText.setText(Integer.toString(autoHighFuel));
        autoLowFuelText = (TextView) findViewById(R.id.autoLowFuel);
        autoLowFuelText.setText(Integer.toString(autoLowFuel));
        noAutoBox = (CheckBox) findViewById(R.id.noAuto);
        noAutoBox.setChecked(MainActivity.db.noAuto);
        movedForwardBox = (CheckBox) findViewById(R.id.movedForward);
        movedForwardBox.setChecked(MainActivity.db.movedForward);
        autoGear = (RadioGroup) findViewById(R.id.autoGear);
        switch (MainActivity.db.autoGear) {
            case 0:
                autoGear.clearCheck();
                break;
            case 1:
                autoGear.check(R.id.autoGearNone);
                break;
            case 2:
                autoGear.check(R.id.autoGearMissed);
                break;
            case 3:
                autoGear.check(R.id.autoGearCenter);
                break;
            case 4:
                autoGear.check(R.id.autoGearBoiler);
                break;
            case 5:
                autoGear.check(R.id.autoGearLoading);
                break;
        }
    }

    // Fuel adding and subtracting
    public void autoHighFuelPlus1(View v) {
        autoHighFuel++;
        autoHighFuelText.setText(Integer.toString(autoHighFuel));
    }

    public void autoHighFuelPlus5(View v) {
        autoHighFuel+=5;
        autoHighFuelText.setText(Integer.toString(autoHighFuel));
    }

    public void autoHighFuelMinus1(View v) {
        if (autoHighFuel > 0) {
            autoHighFuel--;
            autoHighFuelText.setText(Integer.toString(autoHighFuel));
        }
    }

    public void autoHighFuelMinus5(View v) {
        if (autoHighFuel > 4) {
            autoHighFuel-=5;
            autoHighFuelText.setText(Integer.toString(autoHighFuel));
        }
    }

    public void autoLowFuelPlus1(View v) {
        autoLowFuel++;
        autoLowFuelText.setText(Integer.toString(autoLowFuel));
    }

    public void autoLowFuelPlus5(View v) {
        autoLowFuel+=5;
        autoLowFuelText.setText(Integer.toString(autoLowFuel));
    }

    public void autoLowFuelMinus1(View v) {
        if (autoLowFuel > 0) {
            autoLowFuel--;
            autoLowFuelText.setText(Integer.toString(autoLowFuel));
        }
    }

    public void autoLowFuelMinus5(View v) {
        if (autoLowFuel > 4) {
            autoLowFuel-=5;
            autoLowFuelText.setText(Integer.toString(autoLowFuel));
        }
    }

    // Calls activity to go to teleop page
    public void toTeleop(View v) {
        // Save values into SteamworksDatabase
        MainActivity.db.autoLowFuel = autoLowFuel;
        MainActivity.db.autoHighFuel = autoHighFuel;
        MainActivity.db.noAuto = noAutoBox.isChecked();
        MainActivity.db.movedForward = movedForwardBox.isChecked();
        switch (autoGear.getCheckedRadioButtonId()) {
            case R.id.autoGearNone:
                MainActivity.db.autoGear = 1;
                break;
            case R.id.autoGearMissed:
                MainActivity.db.autoGear = 2;
                break;
            case R.id.autoGearCenter:
                MainActivity.db.autoGear = 3;
                break;
            case R.id.autoGearBoiler:
                MainActivity.db.autoGear = 4;
                break;
            case R.id.autoGearLoading:
                MainActivity.db.autoGear = 5;
                break;
            default:
                MainActivity.db.autoGear = 0;
                break;
        }
        // Switch pages
        Intent toTeleop = new Intent(this, goToTeleop.class);
        startActivity(toTeleop);
    }

    // Calls activity to go to prematch page
    public void toPrematch(View v) {
        // Saves values to SteamworksDatabase
        MainActivity.db.autoLowFuel = autoLowFuel;
        MainActivity.db.autoHighFuel = autoHighFuel;
        MainActivity.db.noAuto = noAutoBox.isChecked();
        MainActivity.db.movedForward = movedForwardBox.isChecked();
        switch (autoGear.getCheckedRadioButtonId()) {
            case R.id.autoGearNone:
                MainActivity.db.autoGear = 1;
                break;
            case R.id.autoGearMissed:
                MainActivity.db.autoGear = 2;
                break;
            case R.id.autoGearCenter:
                MainActivity.db.autoGear = 3;
                break;
            case R.id.autoGearBoiler:
                MainActivity.db.autoGear = 4;
                break;
            case R.id.autoGearLoading:
                MainActivity.db.autoGear = 5;
                break;
            default:
                MainActivity.db.autoGear = 0;
                break;
        }
        // Switches pages
        Intent toPrematch = new Intent(this, goToPrematch.class);
        startActivity(toPrematch);
    }
}