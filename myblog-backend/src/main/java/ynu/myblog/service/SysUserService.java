package ynu.myblog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.myblog.dao.SysUserDao;
import ynu.myblog.entity.Token;
import ynu.myblog.service.impl.SysUserImpl;
import ynu.myblog.utils.TokenGenerator;

@Service
public class SysUserService implements SysUserImpl {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysTokenService sysTokenService;

    @Override
    public JSONObject sysuserlogin(String username, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            System.out.println("12");
            int userid =sysUserDao.findUserByPaswd(username,password);
            Token tk = sysTokenService.getTokenById(userid);
            String token = TokenGenerator.getToken();
            System.out.println(token);
            if(tk == null){
                sysTokenService.createToken(userid,token);
            }
            else {
                sysTokenService.updateToken(userid,token);
            }
            jsonObject.put("code",200);
            jsonObject.put("token",token);
            jsonObject.put("username",username);
            jsonObject.put("userid",userid);
            return jsonObject;
        }catch (Exception e){
            System.out.println(e);
            jsonObject.put("code",404);
            return jsonObject;
        }
    }

    @Override
    public  JSONObject sysuserlogout(int userid){
        JSONObject jsonObject = new JSONObject();
        sysTokenService.deleteToken(userid);
        jsonObject.put("code",200);
        return jsonObject;
    }
}
