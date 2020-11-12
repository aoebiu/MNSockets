package xyz.mengnan.MNSockets.service;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.mengnan.MNSockets.mapper.DeviceMapper;
import xyz.mengnan.MNSockets.types.pojo.Device;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceMapper deviceMapper;

    public Page<Device> deviceAll(Integer pn, Integer ps) {
        return null;
    }
}
