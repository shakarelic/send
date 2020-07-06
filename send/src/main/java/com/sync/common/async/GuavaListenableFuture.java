package com.sync.common.async;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @param <T>
 * @author 
 * @date 2016.07.05
 */
public class GuavaListenableFuture<T> implements ListenableFuture<T> {

    private final org.asynchttpclient.ListenableFuture<T> future;

    public GuavaListenableFuture(org.asynchttpclient.ListenableFuture<T> future) {
        this.future = future;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        future.addListener(listener, executor);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }
}
