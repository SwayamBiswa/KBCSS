package com.swayam.kbcss.config;

import com.swayam.kbcss.service.CycleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    private final Logger logger = LoggerFactory.getLogger(CycleServiceImpl.class);


       /* ExecutorService service= new ThreadPoolExecutor(
            10,100,120,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(300),
                new CustomRejectionHandler());*/

    @Bean(name = "executor")
    public Executor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setRejectedExecutionHandler(new CustomRejectionHandler());
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(300);
        executor.setThreadNamePrefix("--");
        executor.initialize();
        return executor;
    }
    class CustomRejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.error(r.toString()+" is  Rejected");
        }
    }
}