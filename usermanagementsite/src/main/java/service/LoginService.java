package service;

import model.UserBean;
import serviceinterface.LoginServiceInterface;
import serviceinterface.SignupServiceInterface;

public class LoginService implements LoginServiceInterface{
    
    public int checkUser(UserBean user) {
        final dao.Userdao Userdao;
        Userdao = new dao.Userdao();
       int usertype =  Userdao.checkUser(user);
        return usertype;
    }
    
}
