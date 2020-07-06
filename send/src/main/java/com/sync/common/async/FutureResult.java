package com.sync.common.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that implements the future completion of a request.
 */
public class FutureResult<T> implements Future<T> {

	private static final Logger logger = LoggerFactory.getLogger(FutureResult.class);
	
    private final CountDownLatch latch = new CountDownLatch(1);
    private volatile Exception error;
    private volatile T result;

    /**
     * Mark this request as complete and unblock any threads waiting on its completion.
     *
     * @param result The result for this request
     * @param error  The error that occurred if there was one, or null.
     */
    public void done(T result, Exception error) {
        this.error = error;
        this.result = result;
        this.latch.countDown();
    }

    /**
     * Await the completion of this request
     */
    public void await() throws InterruptedException {
        latch.await();
    }

    /**
     * Await the completion of this request (up to the given time interval)
     *
     * @param timeout The maximum time to wait
     * @param unit    The unit for the max time
     * @return true if the request completed, false if we timed out
     */
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        return latch.await(timeout, unit);
    }

    /**
     * The result for the request
     */
    public T result() {
        return result;
    }

    /**
     * The error thrown (generally on the server) while processing this request
     */
    public Exception error() {
        return error;
    }

    /**
     * Has the request completed?
     */
    public boolean completed() {
        return this.latch.getCount() == 0L;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return completed();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        this.await();
        return resultOrThrow();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        boolean occurred = this.await(timeout, unit);
        if (!occurred) {
            throw new TimeoutException("Timeout after waiting for " + TimeUnit.MILLISECONDS.convert(timeout, unit) + " ms.");
        }
        return resultOrThrow();
    }

    private T resultOrThrow() throws ExecutionException {
        if (this.error() != null) {
            throw new ExecutionException(this.error());
        } else {
            return result();
        }
    }
    
    /**
     * @param url
     * @param listenableFuture
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getJsonNode(String url, com.google.common.util.concurrent.ListenableFuture<T> listenableFuture) throws Exception {
        try {
            return listenableFuture.get();
        } catch (InterruptedException e) {
            logger.error("url error {} thread interrupted", url, e);
        } catch (ExecutionException e) {
            logger.error("url error {} invoke execute error", url, e);
            throw new Exception(e.getCause());
        }
        return null;
    }
}

