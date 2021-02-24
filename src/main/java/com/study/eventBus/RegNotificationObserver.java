package com.study.eventBus;

public class RegNotificationObserver {

    @Subscribe
    public void handleRegSuccess(Long userId) {
        System.out.println(userId);
    }

}