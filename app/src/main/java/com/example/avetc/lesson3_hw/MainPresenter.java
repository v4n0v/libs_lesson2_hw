package com.example.avetc.lesson3_hw;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Observer;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    String TAG = "Presenter";
//
//    public MainPresenter() {
//
//
//        observer = new Observer<String>()
//        {
//            @Override
//            public void onSubscribe(Disposable d)
//            {
//                Log.d(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(String s)
//            {
//                Log.d(TAG, "onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e)
//            {
//                Log.e(TAG, "onError", e);
//            }
//
//            @Override
//            public void onComplete()
//            {
//                Log.d(TAG, "onComplete");
//            }
//        };
//
//
//    }

    public Observer<String> getObserver() {
        return observer;
    }

    Observer<String> observer;

    public MainPresenter() {



    }

    public void catchText(String text) {
        Log.d(TAG, "input text = "+text);
        getViewState().applyTextChanges(text);

    }


}
