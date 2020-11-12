package xyz.mengnan.MNSockets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.mengnan.MNSockets.sockets.SendMsgThread;
import xyz.mengnan.MNSockets.utils.JsonUtil;
import xyz.mengnan.MNSockets.utils.TypeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

@RestController
@RequestMapping("/api/send")
public class SocketController {
    /**
     * @api {GET} /api/send                    发送指令
     * @apiName send
     * @apiGroup send
     * @apiParam {String}   command           16进制字符串
     * @apiParam {String}   threadName        线程名
     * @apiSuccessExample {json} 成功
     * {
     * "err": 0
     * }
     */
    @GetMapping
    public ResponseEntity send(@RequestParam("command") String command,
                               @RequestParam("threadName") String threadName) throws IOException {

        Thread thread = TypeUtil.getThreadByName(threadName);
        if ("main".equals(threadName) || thread == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonUtil.err());
        }
        // TODO 耦合度过高
        SendMsgThread.setCommand(command);
        LockSupport.unpark(thread);
        return ResponseEntity.ok(JsonUtil.ok());

    }


}
