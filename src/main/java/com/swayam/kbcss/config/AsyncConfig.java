package com.swayam.kbcss.config;

import com.swayam.kbcss.exception.RejectedExecutionHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

    @Bean(name = "executor")
    public Executor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setRejectedExecutionHandler(rejectionHandler);
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("--");
        executor.initialize();
        return executor;
    }
}