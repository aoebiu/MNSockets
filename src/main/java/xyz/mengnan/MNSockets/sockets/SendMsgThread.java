package xyz.mengnan.MNSockets.sockets;

import lombok.Cleanup;
import xyz.mengnan.MNSockets.adapter.AdapterThread;
import xyz.mengnan.MNSockets.utils.TypeUtil;
import io.enoa.toolkit.id.snowflake.SnowflakeKit;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class SendMsgThread extends AdapterThread {

    private Socket socket;
    private static String command;

    public SendMsgThread(Socket socket) {
        super("toClient", SnowflakeKit.string());
        System.out.println(this.getThreadName());
        this.socket = socket;
        // TODO 并将线程名传入数据库
    }

    public synchronized static void setCommand(String command) {
        SendMsgThread.command = command;
    }

    @Override
    public void toStart() throws IOException {
        @Cleanup OutputStream os = socket.getOutputStream();
        while (socket != null) {
            LockSupport.park();
            // 记录了该线程名,之后通过调用Web唤醒该线程
            // TODO 此处可能会有线程不安全问题
            synchronized (SendMsgThread.class) {
                log.info("server ->  " + command);
                byte[] bytes = TypeUtil.hexStringToByteArray(command);
                os.write(bytes);
                os.flush();
            }
        }
        log.error("connect reset");
    }

}

