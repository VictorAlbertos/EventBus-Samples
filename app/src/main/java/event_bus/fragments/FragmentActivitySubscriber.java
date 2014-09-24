package event_bus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ts.samples_event_bus.R;

import de.greenrobot.event.EventBus;
import event_bus.models.EventBusFlag;
import event_bus.services.ServiceSubscriber;

public class FragmentActivitySubscriber extends FragmentActivity {

    private boolean _hasPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        setContentView(R.layout.test_subscriber_fragment_activity);
        setListeners(R.id.send_to_all_receiver, null);
        setListeners(R.id.send_to_receiver_1, FragmentReceiverA.class);
        setListeners(R.id.send_to_receiver_2, FragmentReceiverB.class);
        setListeners(R.id.send_to_receiver_3, FragmentReceiverC.class);

        TextView tv_fragment_activity = (TextView) findViewById(R.id.tv_fragment_activity);
        tv_fragment_activity.setText(FragmentActivitySubscriber.class.getSimpleName());

        startService(new Intent(this, ServiceSubscriber.class));
    }

    private void setListeners(int IDLayout, final Class aClass) {
        findViewById(IDLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new EventBusFlag(FragmentActivitySubscriber.class.getSimpleName())
                                .setFilterReceiverClass(aClass)
                );
            }
        });
    }

    public void onEventMainThread(EventBusFlag eventBusFlag) {
        if (!eventBusFlag.isReallyEventFor(this)) return;

        if(eventBusFlag.getContent() instanceof Integer) {
            Integer colorId = (Integer) eventBusFlag.getContent();
            findViewById(R.id.ll_root_view).setBackgroundColor(colorId);
        } else {
            String message = eventBusFlag.getMessage();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            if(!_hasPaused) startService(new Intent(this, ServiceSubscriber.class));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        _hasPaused = false;
        startService(new Intent(this, ServiceSubscriber.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        _hasPaused = true;
    }
}
