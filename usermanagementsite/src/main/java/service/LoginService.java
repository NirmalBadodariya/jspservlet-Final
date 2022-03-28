package service;

import model.UserBean;
import serviceinterface.LoginServiceInterface;
import serviceinterface.SignupServiceInterface;

public class LoginService implements LoginServiceInterface{
    
    public boolean checkUser(UserBean user) {
        final dao.Userdao Userdao;
        Userdao = new dao.Userdao();
       boolean check =  Userdao.checkUser(user);
        return check;
    }
    
}
