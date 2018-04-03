package com.example.avetc.lesson3_hw;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.avetc.lesson3_hw.utils.EventBus;
import com.example.avetc.lesson3_hw.fragments.Fragment1;
import com.example.avetc.lesson3_hw.fragments.Fragment2;

import com.example.avetc.lesson3_hw.utils.MyEventBus;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;


public class MainActivity extends MvpAppCompatActivity implements MainView {

    String TAG = "MainActivity";

    @BindView(R.id.input)
    EditText inText;
    @BindView(R.id.output)
    TextView outText;
    @BindView(R.id.publish_button)
    Button publishButton;
    @InjectPresenter
    MainPresenter presenter;

    Observable<String> observable1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame1, new Fragment1()).add(R.id.frame2, new Fragment2()).commit();

        // в задании написано использовать addTextChangedListener, но это же бессмысленно (или нет?).
        // Зачем тогда вообще RX?  можно же просто в onTextChanged менять значение TextView
        // В общем-то и тут презентер выходит просто лишней "прослойкой", но сработал
        // принцип "повторение-мать учения!"
        RxTextView.afterTextChangeEvents(inText)
                .subscribe(s -> {
                    presenter.catchText(inText.getText().toString());
                });


        // если после ввода текста, его можно отправить в 2 фрамента
        RxView.clicks(publishButton)
                .map(aVoid -> inText.getText().toString().trim())
                .filter(s -> !TextUtils.isEmpty(s))
                .subscribe(aVoid -> {
                    Log.d(TAG, "publish_button clicked");
//                    MyEventBus.getBus().post(inText.getText().toString());
                     EventBus.getBus().post(inText.getText().toString());
                });
    }


    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void applyTextChanges(String text) {
        outText.setText(text);
    }
}
