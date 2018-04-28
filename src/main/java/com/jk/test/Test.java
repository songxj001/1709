package com.jk.test;

import com.jk.pool.ThreadPoolUtil;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        String name = "张三";
        ThreadPoolUtil.executeSingleThread(new TestThread(name));

        Thread thread = new Thread();
        thread.start();
        //

    }
}
