package com.nuist.thread;

import java.util.List;

/**
 * @param <Job> 执行的任务
 * @author LwolveJ
 */
public interface MyThreadPool<Job extends Runnable> {

    void execute(Runnable task);

    void execute(Runnable[] tasks);

    void execute(List<Runnable> tasks);

    void destroy();
}
