package event_bus.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import event_bus.fragments.FragmentReceiver;
import event_bus.fragments.FragmentReceiverA;
import event_bus.fragments.FragmentReceiverB;
import event_bus.fragments.FragmentReceiverC;

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentReceiver> _fragmentReceivers;

    public FragmentViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        _fragmentReceivers = new ArrayList<FragmentReceiver>(Arrays.asList(
                new FragmentReceiverA(), new FragmentReceiverB(), new FragmentReceiverC()
        ));
    }

    @Override
    public Fragment getItem(int position) {
        return _fragmentReceivers.get(position);
    }

    @Override
    public int getCount() {
        return _fragmentReceivers.size();
    }
}
