package com.example.avetc.lesson3_hw.utils;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

 

public class MyEventBus {
    public static MyEventBus getBus() {
        return bus;
    }

    private static MyEventBus bus = new MyEventBus();


    private PublishSubject<String> subject = PublishSubject.create();
    public void subscribe(Observer<String> observer){
        subject.subscribe(observer);
    }

    public void post(String msg){
        subject.onNext(msg);
    }
}
