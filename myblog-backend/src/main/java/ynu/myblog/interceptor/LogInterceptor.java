package ynu.myblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ynu.myblog.dao.SysUserDao;
import ynu.myblog.dao.TokenDao;
import ynu.myblog.entity.SysUser;
import ynu.myblog.entity.Token;
import ynu.myblog.service.SysTokenService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*

 */
public class LogInterceptor implements HandlerInterceptor {

    /*

     */
    @Autowired
    TokenDao tokenDao;

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysTokenService sysTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=null;
        System.out.println("prehandle");
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    System.out.println("拦截器拦截到的cookie=="+token);
                    break;
                }
            }
        }

        try {
            int id =tokenDao.getIdByToken(token);
            return true;
        }catch (Exception e){
            System.out.println("error");
            return true;
        }

    }



}
