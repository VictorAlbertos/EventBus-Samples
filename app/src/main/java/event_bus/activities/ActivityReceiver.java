package event_bus.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import event_bus.models.EventBusFlag;

public class ActivityReceiver extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().registerSticky(this);
    }

    public void onEvent(EventBusFlag eventBusFlag) {
        if(!eventBusFlag.isEventFor(this)) return;
        Toast.makeText(this, eventBusFlag.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().postSticky(new EventBusFlag(ActivityReceiver.class.getSimpleName()));
    }

}
