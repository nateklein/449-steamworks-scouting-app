package robo.scoutingappmockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class goToEndgame extends Activity {

    private int catchTimeValue;
    private int climbTimeValue;

    // Input fields
    private TextView climbTime;
    private TextView catchTime;
    private RadioGroup climb;
    private EditText comments;
    // Displays endgame page on activity call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame_page);
        climbTime = (TextView) findViewById(R.id.climbTime);
        climbTime.setText(Integer.toString(climbTimeValue));
        catchTime = (TextView) findViewById(R.id.catchTime);
        catchTime.setText(Integer.toString(catchTimeValue));
        climb = (RadioGroup) findViewById(R.id.climb);
        switch (MainActivity.db.climb) {
            case 0:
                climb.clearCheck();
                break;
            case 1:
                climb.check(R.id.climbNoAttempt);
                break;
            case 2:
                climb.check(R.id.climbSuccess);
                break;
            case 3:
                climb.check(R.id.climbFail);
                break;
        }
        comments = (EditText) findViewById(R.id.comments);
        comments.setText(MainActivity.db.comment);
    }

    public void increaseClimbTime(View v) {
        climbTimeValue++;
        climbTime.setText(Integer.toString(climbTimeValue));
    }

    public void decreaseClimbTime(View v) {
        if (climbTimeValue > 0) {
            climbTimeValue--;
            climbTime.setText(Integer.toString(climbTimeValue));
        }
    }

    public void increaseCatchTime(View v) {
        catchTimeValue++;
        catchTime.setText(Integer.toString(catchTimeValue));
    }

    public void decreaseCatchTime(View v) {
        if (catchTimeValue > 0) {
            catchTimeValue--;
            catchTime.setText(Integer.toString(catchTimeValue));
        }
    }

    // Calls activity to go to teleop page
    public void toTeleop(View v) {
        // Saves values to SteamworksDatabase
        switch (climb.getCheckedRadioButtonId()) {
            case R.id.climbNoAttempt:
                MainActivity.db.climb = 1;
                break;
            case R.id.climbSuccess:
                MainActivity.db.climb = 2;
                break;
            case R.id.climbFail:
                MainActivity.db.climb = 3;
                break;
            default:
                MainActivity.db.climb = 0;
                break;
        }
        MainActivity.db.comment = comments.getText().toString();
        MainActivity.db.catchTime = catchTimeValue;
        MainActivity.db.climbTime = climbTimeValue;
        // Switches pages
        Intent toTeleop = new Intent(this, goToTeleop.class);
        startActivity(toTeleop);
    }

    // Calls activity to submit
    public void submit(View v) {
        switch (climb.getCheckedRadioButtonId()) {
            case R.id.climbNoAttempt:
                MainActivity.db.climb = 1;
                break;
            case R.id.climbSuccess:
                MainActivity.db.climb = 2;
                break;
            case R.id.climbFail:
                MainActivity.db.climb = 3;
                break;
            default:
                MainActivity.db.climb = 0;
                break;
        }
        MainActivity.db.comment = comments.getText().toString();
        MainActivity.db.catchTime = catchTimeValue;
        MainActivity.db.climbTime = climbTimeValue;
        Intent submit = new Intent(this, submit.class);
        startActivity(submit);
    }
}