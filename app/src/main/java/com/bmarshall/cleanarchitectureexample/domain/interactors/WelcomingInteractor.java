package com.bmarshall.cleanarchitectureexample.domain.interactors;

import com.bmarshall.cleanarchitectureexample.domain.interactors.base.Interactor;

/**
 * Created by bmarshall on 2/9/17.
 */

public interface WelcomingInteractor extends Interactor {

    interface Callback {

        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }
}
