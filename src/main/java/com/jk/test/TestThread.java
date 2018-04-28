package com.jk.test;

public class TestThread implements Runnable{

    private String name;

    @Override
    public void run() {
        System.out.println("线程执行了"+name);
    }

    public TestThread(String name) {
        this.name = name;
    }

    public TestThread() {
    }
}
