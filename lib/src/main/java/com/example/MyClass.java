package com.example;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MyClass {
    public static void main(String[] args){
        long timeNow = System.currentTimeMillis();
        Observable.zip(
                Observable.range(1, 5)
//                        .groupBy(n -> n % 5)
//                        .flatMap(g -> g.toList())
                ,
                Observable.interval(50, TimeUnit.MILLISECONDS),
                (obs, timer) -> obs
        )
                .doOnSubscribe(a -> System.out.println("start 1 " + a))
                .doOnNext(i -> System.out.println("Item: " + i + ", Time: " + (System.currentTimeMillis() - timeNow) + "ms"))
                .toList()
                .repeat(5)
                .subscribe()
//                .toBlocking()
//                .first()
        ;
    }
}
