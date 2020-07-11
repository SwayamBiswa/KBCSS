package com.swayam.kbcss.exception;

import lombok.SneakyThrows;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @SneakyThrows
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
       throw new Exception( r.toString() + " is rejected");
    }
}
