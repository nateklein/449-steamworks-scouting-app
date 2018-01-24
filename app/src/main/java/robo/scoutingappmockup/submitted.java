package robo.scoutingappmockup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class submitted extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitted_page);
    }

    public void nextMatch(View v) {
        Intent nextMatch = new Intent(this, MainActivity.class);
        startActivity(nextMatch);
    }
}