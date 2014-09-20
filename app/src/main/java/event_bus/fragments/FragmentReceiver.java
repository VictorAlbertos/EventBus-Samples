package event_bus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ts.samples_event_bus.R;

import de.greenrobot.event.EventBus;
import event_bus.models.EventBusFlag;

public class FragmentReceiver extends Fragment {

    private int nEvents;
    protected int _colorBackground;

    private TextView tv_receiver, tv_received_event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return inflater.inflate(R.layout.test_receiver_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_receiver = (TextView) getView().findViewById(R.id.tv_receiver);
        tv_received_event =  (TextView) getView().findViewById(R.id.tv_received_event);
        tv_receiver.setText(getClass().getSimpleName());
    }

    public void onEvent(EventBusFlag eventBusFlag) {
        if (!eventBusFlag.isEventFor(this)) return;
        nEvents++;
        tv_received_event.setText("Event " + nEvents + " received from " + eventBusFlag.getMessage());
    }


    public int getColorBackground() {
        return _colorBackground;
    }

}
