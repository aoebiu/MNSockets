package xyz.mengnan.MNSockets.patch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.mengnan.MNSockets.utils.JsonUtil;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class SysExceptionHandler {
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public JsonUtil exceptionHandler(NullPointerException e) {
        log.error(e.getMessage(), e);
        return JsonUtil.err("服务器错误", e);
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public JsonUtil exceptionHandler(SQLException e) {
        log.error(e.getMessage(), e);
        return JsonUtil.err("服务器错误", e);
    }
}
