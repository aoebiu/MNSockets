package xyz.mengnan.MNSockets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolConfig {

    @Bean
    public ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(20, 80, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }
}
