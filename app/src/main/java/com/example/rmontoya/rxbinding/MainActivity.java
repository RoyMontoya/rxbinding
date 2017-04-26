package com.example.rmontoya.rxbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

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
        final Subscription firstButtonSubscription = RxView.clicks(firstButton)
                .subscribe(aVoid -> {
                    firstNumber++;
                    mainTextView.setText(String.valueOf(firstNumber));
                });

        RxView.clicks(unsubscribeFirstButton)
                .subscribe(aVoid -> firstButtonSubscription.unsubscribe());
    }

}
