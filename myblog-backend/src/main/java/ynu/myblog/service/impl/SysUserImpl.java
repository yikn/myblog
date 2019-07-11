package ynu.myblog.service.impl;

import com.alibaba.fastjson.JSONObject;

public interface SysUserImpl {
    JSONObject sysuserlogin(String username, String password);

    JSONObject sysuserlogout(int userid);
}
