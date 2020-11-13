package xyz.mengnan.MNSockets.config;

import lombok.RequiredArgsConstructor;
import xyz.mengnan.MNSockets.sockets.SocketThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketsConfig implements CommandLineRunner {
    private static final Integer port = 8081;
    private ServerSocket server;
    private final Executor threadPool;

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
