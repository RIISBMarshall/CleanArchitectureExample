package com.bmarshall.cleanarchitectureexample.presentation.presenters.impl;

import com.bmarshall.cleanarchitectureexample.domain.executor.Executor;
import com.bmarshall.cleanarchitectureexample.domain.executor.MainThread;
import com.bmarshall.cleanarchitectureexample.domain.interactors.WelcomingInteractor;
import com.bmarshall.cleanarchitectureexample.domain.interactors.impl.WelcomingInteractorImpl;
import com.bmarshall.cleanarchitectureexample.domain.repository.MessageRepository;
import com.bmarshall.cleanarchitectureexample.presentation.presenters.base.AbstractPresenter;
import com.bmarshall.cleanarchitectureexample.presentation.presenters.MainPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        WelcomingInteractor.Callback {

    private MainPresenter.View mView;
    private MessageRepository mMessageRepository;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view, MessageRepository messageRepository) {
        super(executor, mainThread);
        mView = view;
        mMessageRepository = messageRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();

        // initialize the interactor
        WelcomingInteractor interactor = new WelcomingInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mMessageRepository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
        mView.hideProgress();
        mView.displayWelcomeMessage(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}