package com.example.rmontoya.rxbinding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    int firstNumber = 0;
    int secondNumber = 100;
    @BindView(R.id.first_text)
    TextView firstTextView;
    @BindView(R.id.second_text)
    TextView secondTextView;
    @BindView(R.id.main_button)
    Button mainButton;
    @BindView(R.id.unsubscribe_first_button)
    Button unsubscribeFirstButton;
    @BindView(R.id.unsubscribe_second_button)
    Button unsubscribeSecondButton;
    @BindView(R.id.next_activity_button)
    Button nextActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSubscriptionViews();
    }

    private void setSubscriptionViews() {
        Observable<Void> firstButtonObservable = RxView.clicks(mainButton).share();
        Subscription firstButtonSubscription = firstButtonObservable.subscribe(aVoid -> {
            firstNumber++;
            firstTextView.setText(String.valueOf(firstNumber));
        });
        Subscription seconButtonSubscription = firstButtonObservable.subscribe(aVoid -> {
            secondNumber--;
            secondTextView.setText(String.valueOf(secondNumber));
        });
        RxView.clicks(unsubscribeFirstButton).subscribe(aVoid -> firstButtonSubscription.unsubscribe());
        RxView.clicks(unsubscribeSecondButton).subscribe(aVoid -> seconButtonSubscription.unsubscribe());
        RxView.clicks(nextActivityButton).subscribe(aVoid -> startSecondActivity());
    }

    private void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
