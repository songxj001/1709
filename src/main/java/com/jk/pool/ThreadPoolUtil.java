package com.jk.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    //缓存线程池
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    //定长线程池
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    //定长定时线程池
    static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

    //单个线程池
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    /**
     * 缓存线程池
     * @param runnable
     */
    public static void executeCacheThread(Runnable runnable){
        cachedThreadPool.execute(runnable);
    }

    /**
     * 定长线程池
     * @param runnable
     */
    public static void executeFixedThread(Runnable runnable){
        fixedThreadPool.execute(runnable);
    }

    /**
     * 定长定时线程池
     * @param runnable
     * @param time
     * @param unit
     */
    public static void executeScheduledThread(Runnable runnable, long time, TimeUnit unit){
        scheduledThreadPool.schedule(runnable,time,unit);
    }

    /**
     * 单个线程池
     * @param runnable
     */
    public static void executeSingleThread(Runnable runnable){
        singleThreadExecutor.execute(runnable);
    }


}
