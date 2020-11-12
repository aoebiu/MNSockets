package xyz.mengnan.MNSockets.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.mengnan.MNSockets.service.DeviceService;
import xyz.mengnan.MNSockets.types.pojo.Device;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
public class InfoController {
    private final DeviceService deviceService;

    @GetMapping("/allDevice")
    public ResponseEntity allDevice(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "ps", defaultValue = "30") Integer ps) {
        Page<Device> dat = this.deviceService.deviceAll(pn, ps);
        return ResponseEntity.ok(dat);
    }
}
