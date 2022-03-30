package service;

import model.UserBean;
import serviceinterface.LoginServiceInterface;
import serviceinterface.SignupServiceInterface;

public class LoginService implements LoginServiceInterface{
    private dao.Userdao Userdao;
    
    public int checkUser(UserBean user) {
        Userdao = new dao.Userdao();
       int usertype =  Userdao.checkUser(user);
        return usertype;
    }
    
}
