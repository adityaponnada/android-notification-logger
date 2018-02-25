package microengage.mhealth.android.neu.edu.notifcountersample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button)findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start the service here

                Log.d("NOTIF_MAIN: ", "START SERVICE CLICKED");

                Intent intent = new Intent(getApplicationContext(), NotifCounterService.class);
                startService(intent);
            }
        });


    }
}
