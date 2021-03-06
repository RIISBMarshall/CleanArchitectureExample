package com.bmarshall.cleanarchitectureexample.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bmarshall.cleanarchitectureexample.R;
import com.bmarshall.cleanarchitectureexample.domain.executor.impl.ThreadExecutor;
import com.bmarshall.cleanarchitectureexample.presentation.presenters.MainPresenter;
import com.bmarshall.cleanarchitectureexample.presentation.presenters.MainPresenter.View;
import com.bmarshall.cleanarchitectureexample.presentation.presenters.impl.MainPresenterImpl;
import com.bmarshall.cleanarchitectureexample.storage.WelcomeMessageRepository;
import com.bmarshall.cleanarchitectureexample.threading.MainThreadImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View {

    @Bind(R.id.welcome_textview)
    TextView mWelcomeTextView;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // create a presenter for this view
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new WelcomeMessageRepository()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
