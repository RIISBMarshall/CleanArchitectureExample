package com.bmarshall.cleanarchitectureexample.storage;

import com.bmarshall.cleanarchitectureexample.domain.repository.MessageRepository;

/**
 * Created by bmarshall on 2/9/17.
 */

public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly


        // let's simulate some network/database lag
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return msg;
    }
}
