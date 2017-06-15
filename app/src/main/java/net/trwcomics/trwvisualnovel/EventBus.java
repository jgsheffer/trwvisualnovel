package net.trwcomics.trwvisualnovel;

import com.squareup.otto.Bus;

/**
 * Created by Jared on 6/14/2017.
 */

public class EventBus {
    static Bus bus = new Bus();
    public static Bus getBus(){
        return bus;
    }

}
