package service;

import model.UserBean;
import serviceinterface.LoginServiceInterface;

public class LoginService implements LoginServiceInterface{
    private dao.Userdao Userdao;
    
    public String checkUser(UserBean user) {
        Userdao = new dao.Userdao();
       return  Userdao.checkUser(user);
       
    }
    
}
