package xyz.mengnan.MNSockets.sockets;

import lombok.Cleanup;
import xyz.mengnan.MNSockets.adapter.AdapterThread;
import xyz.mengnan.MNSockets.utils.SocketUtil;
import xyz.mengnan.MNSockets.utils.TypeUtil;
import io.enoa.toolkit.id.snowflake.SnowflakeKit;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

@Slf4j
public class SocketThread extends AdapterThread {
    private ServerSocket server;
    private Socket socket;
    //    @Value("${socket.port}")
    private Integer port = 8081;

    {
        log.debug("socket running in port :" + this.port);
        this.setDaemon(true);
    }

    public SocketThread(Socket socket) {
        super("fromClient", SnowflakeKit.string());
        log.info(this.getThreadName());
        this.socket = socket;
    }

    // 测试用例构造方法
    private SocketThread(Integer port) throws IOException {
        super();
        server = new ServerSocket(port);
        socket = server.accept();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void toStart() throws IOException, InterruptedException {
        log.info(socket.getRemoteSocketAddress() + " -> " + "success to connect...");
        SendMsgThread sendMessThread = new SendMsgThread(socket);
        sendMessThread.start();
        @Cleanup InputStream in = socket.getInputStream();

        int len;
        byte[] buf = new byte[12];

        try {
            while ((len = in.read(buf)) != -1) {
                log.info("client -> original : " + TypeUtil.bytesToHexString(buf));
                log.info("translate :" + new String(buf, 0, len));
            }
        } catch (SocketException e) {
            SocketUtil.close(socket);
            log.error("connection reset", e);
            Thread.sleep(800);
        } finally {
            // this结束了,这个线程也没有运行的意义了
            sendMessThread.stop();
        }
    }

    // Socket Test
    public static void main(String[] args) throws IOException {
        SocketThread server = new SocketThread(8081);
        server.start();
    }
}
