package com.example.avetc.lesson3_hw.utils;

import com.squareup.otto.Bus;

public class EventBus {
    public static Bus getBus() {
        return bus;
    }

    private static Bus bus = new Bus();
}
