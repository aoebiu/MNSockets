package xyz.mengnan.MNSockets.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TypeUtil {
    private TypeUtil() {
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            char c;
            try {
                c = hexString.charAt(i + 1);
            } catch (StringIndexOutOfBoundsException e) {
                // e.printStackTrace();
                log.error("{} is not illegal", hexString);
                return new byte[]{0x00, 0x00};
            }
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(c, 16));
        }
        return bytes;
    }

    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) {
                return t;
            }
        }
        return null;
    }
}

