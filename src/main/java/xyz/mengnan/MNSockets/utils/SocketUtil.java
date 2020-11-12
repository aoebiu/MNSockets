package xyz.mengnan.MNSockets.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketUtil {
    public static void close(Socket socket) throws IOException {
        if (socket != null) {
            socket.close();
            return;
        }
        throw new NullPointerException("Socket is null");
    }

    public static void close(OutputStream os) throws IOException {
        if (os != null) {
            os.close();
            return;
        }
        throw new NullPointerException("OutputStream is null");
    }

    public static void close(ServerSocket server) throws IOException {
        if (server != null) {
            server.close();
            return;
        }
        throw new NullPointerException("Server is null");
    }
}
