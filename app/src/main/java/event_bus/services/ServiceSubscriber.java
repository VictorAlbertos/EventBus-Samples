package event_bus.services;


import android.app.IntentService;
import android.content.Intent;

import java.util.Random;

import de.greenrobot.event.EventBus;
import event_bus.fragments.FragmentActivitySubscriber;
import event_bus.models.EventBusFlag;

public class ServiceSubscriber extends IntentService {

    public ServiceSubscriber() {
        super("ServiceSubscriber");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int time = timeToWait();
        waitForNothing(time);
        String message = "Waiting from " + ServiceSubscriber.class.getSimpleName() + " "
                + time + " milliseconds";

        EventBus.getDefault().post(
                new EventBusFlag(message).setFilterReceiverClass(FragmentActivitySubscriber.class)
        );
    }

    private void waitForNothing(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    public int timeToWait() {
        Random random = new Random();
        return random.nextInt((10000 - 1000) + 1) + 1000;
    }

}