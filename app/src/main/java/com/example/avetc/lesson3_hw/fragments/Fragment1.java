package com.example.avetc.lesson3_hw.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.avetc.lesson3_hw.R;
import com.example.avetc.lesson3_hw.utils.EventBus;
import com.example.avetc.lesson3_hw.utils.MyEventBus;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;



public class Fragment1 extends android.support.v4.app.Fragment{
    @BindView(R.id.text_frame1)
    TextView textView1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        ButterKnife.bind( this, view);
//        initObserver();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        EventBus.getBus().register(this);

//        MyEventBus.getBus().subscribe(observer);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getBus().unregister(this);
    }


    @Subscribe
    public void recievedMessage(String msg){
        textView1.setText(msg);
    }


//    private Observer<String> observer;
//    void initObserver() {
//        observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                recievedMessage(s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//    }

}
