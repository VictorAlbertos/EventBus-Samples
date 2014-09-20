package event_bus.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ts.samples_event_bus.R;

import event_bus.fragments.FragmentActivitySubscriber;

public class ActivityLauncher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        startActivity(R.id.bt_sample_fragments, FragmentActivitySubscriber.class);
        startActivity(R.id.bt_sample_activities, ActivitySubscriber.class);
    }

    private void startActivity(int idButton, final Class activity) {
        findViewById(idButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLauncher.this, activity));
            }
        });
    }
}
