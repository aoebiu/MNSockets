package xyz.mengnan.MNSockets.types.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Device implements Serializable {
    private String did;
    private String threadName;
    private String ctime;
}
