package com.lanna.android.simplechat.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by lanna on 4/20/17.
 */

public class ChatServiceModel {

//    public String[] FAKE_MESSAGES = {"lanna", "lanna 1", "lanna 2", "lanna 3", "lanna 4", "lanna 5", "lanna 6"};

    private int userId;

    private Random generator = new Random();

    public ChatServiceModel(int userId) {
        this.userId = userId;
    }

    public void startNormalMessageFlow(Observer<String> subscriber) {
        long startTime = System.currentTimeMillis();
        Observable.zip(
                Observable.range(1, 15)
//                        .groupBy(n -> n % 5)
                        .map(n -> randomString(n * 30))
                ,
                Observable.interval(2500, TimeUnit.MILLISECONDS),
                (obs, timer) -> (System.currentTimeMillis() - startTime) + "ms: " + obs
        )
//                .doOnSubscribe(a -> System.out.println("start ======== " + a))
//                .doOnNext(item -> System.out.println("Item: " + item + ", Time: " + (System.currentTimeMillis() - startTime) + "ms"))
                .repeat()
                .subscribe(subscriber)
        ;

//        Observable.range(1, 5)
//                .concatMap(i-> Observable.just(i).delay(50, TimeUnit.MILLISECONDS))
//                .doOnSubscribe(a -> System.out.println("start 2 " + a))
//                .doOnNext(i-> System.out.println("Item: " + i + ", Time: " + (System.currentTimeMillis() - startTime) +"ms"))
////                .delay(500, TimeUnit.MILLISECONDS)
//                .repeat(2)
//                .subscribe(subscriber)
//        ;
    }

    private String randomString(int maxLen) {
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(maxLen);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
