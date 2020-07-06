package com.sync.task.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 
 * @date 16-11-3 下午3:50.
 */
public class FutureUtils {

    private static final Logger logger = LoggerFactory.getLogger(FutureUtils.class);

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
            logger.error("url {} thread interrupted", url, e);
        } catch (ExecutionException e) {
            logger.error("url {} invoke execute error", url, e);
            throw new Exception(e.getCause());
        }
        return null;
    }

}
