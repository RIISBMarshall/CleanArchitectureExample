package com.bmarshall.cleanarchitectureexample.presentation.presenters;

import com.bmarshall.cleanarchitectureexample.presentation.presenters.base.BasePresenter;
import com.bmarshall.cleanarchitectureexample.presentation.ui.BaseView;


public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        void displayWelcomeMessage(String msg);
    }
}
