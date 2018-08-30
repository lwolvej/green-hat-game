package com.nuist.thread;


import com.nuist.exception.MyException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LwolveJ
 */
public class MyThreadPoolImpl implements MyThreadPool {

    /**
     * 默认线程数
     */
    private static int DEFAULT_WORKER_NUMBER = 10;

    /**
     * 任务完成数
     */
    private static volatile int sumCount = 0;

    /**
     * 任务队列
     */
    private static final BlockingQueue<Runnable> TASK_QUEUE = new LinkedBlockingDeque<>();

    /**
     * 线程工作组
     */
    private WorkerThread[] workerThreads;

    private static AtomicLong threadNumber = new AtomicLong();

    private static MyThreadPoolImpl threadPool;


    public MyThreadPoolImpl() {
        this(DEFAULT_WORKER_NUMBER);
    }

    public MyThreadPoolImpl(int workNum) {
        DEFAULT_WORKER_NUMBER = workNum;
        workerThreads = new WorkerThread[DEFAULT_WORKER_NUMBER];
        for (int i = 0; i < DEFAULT_WORKER_NUMBER; i++) {
            workerThreads[i] = new WorkerThread();
            Thread thread = new Thread(workerThreads[i], "Now" + threadNumber.incrementAndGet());
            thread.start();
        }
    }

    public static MyThreadPool getThreadPool() {
        return getThreadPool(DEFAULT_WORKER_NUMBER);
    }

    public static MyThreadPool getThreadPool(int workNum) {
        if (workNum < 0) {
            workNum = DEFAULT_WORKER_NUMBER;
        }
        if (threadPool == null) {
            return new MyThreadPoolImpl(workNum);
        }
        return threadPool;
    }


    @Override
    public void execute(Runnable task) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.add(task);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void execute(Runnable[] tasks) {
        synchronized (TASK_QUEUE) {
            Collections.addAll(TASK_QUEUE, tasks);
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void execute(List tasks) {
        synchronized (TASK_QUEUE) {
            for (Object task : tasks) {
                TASK_QUEUE.add((Runnable) task);
            }
            TASK_QUEUE.notifyAll();
        }
    }

    @Override
    public void destroy() {
        while (!TASK_QUEUE.isEmpty()) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new MyException("Thread error!");
            }
        }
        for (int i = 0; i < DEFAULT_WORKER_NUMBER; i++) {
            workerThreads[i].setWorkerFlag();
            workerThreads[i] = null;
        }
        threadPool = null;
        TASK_QUEUE.clear();
    }

    class WorkerThread extends Thread {
        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable runnable = null;
            while (isRunning) {
                synchronized (TASK_QUEUE) {
                    while (isRunning && TASK_QUEUE.isEmpty()) {
                        try {
                            TASK_QUEUE.wait(15);
                        } catch (InterruptedException e) {
                            throw new MyException("Thread wrong");
                        }
                    }
                    if (!TASK_QUEUE.isEmpty()) {
                        runnable = TASK_QUEUE.poll();
                    }
                }
                if (runnable != null) {
                    runnable.run();
                }
                sumCount++;
                runnable = null;
            }
        }

        void setWorkerFlag() {
            isRunning = false;
        }
    }

    @Override
    public String toString() {
        return "线程数量" + DEFAULT_WORKER_NUMBER + " 已完成任务数量" + sumCount + " 等待任务数量" + TASK_QUEUE.size();
    }
}
