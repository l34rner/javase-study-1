package com.edgar.rxjava.hello;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2015/10/10.
 */
public class Map {
    public static void main(String[] args) {

        Observable.just("#Basic Markdown to HTML")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        if(s != null && s.startsWith("#")) {
                            return "<h1>" + s.substring(1, s.length()) + "</h1>";
                        }
                        return null;
                    }
                }).subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world")
                .map(s -> s.hashCode())
                .subscribe(s -> System.out.println(s));

        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }
}
