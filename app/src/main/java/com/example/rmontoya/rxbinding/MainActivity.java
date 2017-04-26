package com.example.rmontoya.rxbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    int firstNumber = 0;
    @BindView(R.id.first_text)
    TextView mainTextView;
    @BindView(R.id.main_button)
    Button firstButton;
    @BindView(R.id.unsubscribe_first_button)
    Button unsubscribeFirstButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSubscriptionViews();
    }

    private void setSubscriptionViews() {
        Observable<Void> firstButtonObservable = RxView.clicks(firstButton).share();
        firstButtonObservable.subscribe(aVoid -> {
            firstNumber++;
            mainTextView.setText(String.valueOf(firstNumber));
        });
        firstButtonObservable.subscribe(aVoid -> Log.d("SUBSCRIBER", "" + firstNumber));

//        RxView.clicks(unsubscribeFirstButton)
//                .subscribe(aVoid -> firstButtonSubscription.unsubscribe());
    }

}
