package ynu.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ynu.myblog.dao.TokenDao;
import ynu.myblog.entity.Token;

@Service
public class SysTokenService implements ynu.myblog.service.impl.SysTokenImpl {

    @Autowired
    private TokenDao tokenDao;

    boolean createToken(int userid ,String token){
        return tokenDao.createToken(userid,token);
    };


    int getIdByToken(String token){
        return tokenDao.getIdByToken(token);
    };

    Token getTokenById(int id){return tokenDao.getTokenById(id);}


    boolean updateToken(int userid,String token){
        return tokenDao.updateToken(userid,token);
    };


    boolean deleteToken(int userid){
        return tokenDao.deleteToken(userid);
    };
}
