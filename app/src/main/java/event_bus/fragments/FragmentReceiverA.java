package event_bus.fragments;

import android.os.Bundle;

import com.ts.samples_event_bus.R;

public class FragmentReceiverA extends FragmentReceiver {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _colorBackground = getResources().getColor(R.color.light_green);
        getView().setBackgroundColor(_colorBackground);
    }

}
