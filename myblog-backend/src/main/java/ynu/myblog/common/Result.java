package ynu.myblog.common;

import java.time.temporal.ValueRange;
import java.util.HashMap;

public class Result extends HashMap<String,Object> {

    public Result(){
        put("code",200);
        put("msg","sucess");
    }

    public static Result error(Integer code,String msg){
        return new Result().put("code",code).put("msg",msg);
    }

    public Result put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
