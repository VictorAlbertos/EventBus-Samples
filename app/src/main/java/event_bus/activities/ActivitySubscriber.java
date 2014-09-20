package event_bus.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.ts.samples_event_bus.R;

import de.greenrobot.event.EventBus;
import event_bus.models.EventBusFlag;

public class ActivitySubscriber extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_subscriber_activity);
        EventBus.getDefault().register(this);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.bt_go_to_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                EventBus.getDefault().postSticky(new EventBusFlag(ActivitySubscriber.class.getSimpleName()));
                startActivity(new Intent(ActivitySubscriber.this, ActivityReceiver.class));
            }
        });
    }

    public void onEvent(EventBusFlag eventBusFlag) {
        if(!eventBusFlag.isEventFor(this)) return;
        Toast.makeText(this, eventBusFlag.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
