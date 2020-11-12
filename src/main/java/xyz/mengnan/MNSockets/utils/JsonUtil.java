package xyz.mengnan.MNSockets.utils;

import io.enoa.toolkit.is.Is;
import io.enoa.toolkit.map.EoMap;
import io.enoa.toolkit.map.Kv;
import io.enoa.toolkit.sys.ThrowableKit;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil extends HashMap<String, Object> implements EoMap<JsonUtil> {

    private Kv kv;

    @Override
    public Map<String, Object> map() {
        return this.kv;
    }

    public JsonUtil() {
        this.kv = Kv.create();
    }

    public static JsonUtil with(boolean ok) {
        return with(ok, null);
    }

    public static JsonUtil with(boolean ok, String msg) {
        if (!ok)
            return err(msg);
        JsonUtil resp = ok();
        if (Is.truthy(msg))
            resp.set("msg", msg);
        return resp;
    }

    public static JsonUtil ok() {
        return new JsonUtil().set("err", 0);
    }

    public static JsonUtil err(Throwable throwable) {
        return err(null, throwable);
    }

    public static JsonUtil err(String msg) {
        return err(msg, null);
    }

    public static JsonUtil err(String msg, Throwable throwable) {
        JsonUtil resp = new JsonUtil();
        resp.set("err", 1);
        if (Is.truthy(msg)) {
            resp.set("msg", msg);
        }
        if (Is.not().nullx(throwable)) {
            resp.set("trace", ThrowableKit.string(throwable));
        }
        return resp;
    }

    public static JsonUtil err() {
        return err(null, null);
    }


}
