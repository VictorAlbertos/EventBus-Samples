package event_bus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ts.samples_event_bus.R;

import de.greenrobot.event.EventBus;
import event_bus.adapters.FragmentViewPagerAdapter;
import event_bus.models.EventBusFlag;

public class FragmentViewPagerReceiver extends Fragment {

    private ViewPager vp_subscriber;
    private FragmentStatePagerAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.subscriber_fragment_view_pager, container, false);
        vp_subscriber = (ViewPager) root.findViewById(R.id.vp_subscriber);
        _adapter = new FragmentViewPagerAdapter(getChildFragmentManager());
        vp_subscriber.setAdapter(_adapter);
        vp_subscriber.setOffscreenPageLimit(2);
        setListener();
        return root;
    }

    private void setListener() {
        vp_subscriber.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((FragmentStatePagerAdapter) vp_subscriber.getAdapter()).getItem(position);

                EventBusFlag flag = new EventBusFlag(getClass().getSimpleName());
                flag.setFilterReceiverClass(FragmentActivitySubscriber.class);
                flag.addContent(((FragmentReceiver)fragment).getColorBackground());

                EventBus.getDefault().post(flag);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
