package xyz.mengnan.MNSockets.mapper;

import org.springframework.stereotype.Repository;
import xyz.mengnan.MNSockets.types.pojo.Device;

import java.util.List;

@Repository
public interface DeviceMapper {
    List<Device> searchAll();
}
