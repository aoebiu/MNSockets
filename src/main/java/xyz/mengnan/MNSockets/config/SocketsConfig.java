package xyz.mengnan.MNSockets.config;

import xyz.mengnan.MNSockets.sockets.SocketThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SocketsConfig implements CommandLineRunner {
    private static final Integer port = 8081;
    private ServerSocket server;
    private Executor threadPool =
            new ThreadPoolExecutor(5, 80, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Bean
    public void initSocket() throws IOException {
        server = new ServerSocket(port);
    }

    public void SocketServer() throws IOException, InterruptedException {
        log.info("wait client connect...");

        while (server != null) {
            Socket client = server.accept();
            Thread.sleep(800);
            threadPool.execute(new SocketThread(client));
        }
    }

    @Override
    public void run(String... strings) throws IOException, InterruptedException {
        SocketServer();
    }
}
