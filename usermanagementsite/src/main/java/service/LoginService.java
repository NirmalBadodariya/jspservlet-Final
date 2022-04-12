package service;

import model.UserBean;
import dao.Userdao;
import serviceinterface.LoginServiceInterface;

public class LoginService implements LoginServiceInterface{
    Userdao Userdao = new Userdao();
    
    public String checkUser(UserBean user) {
       return  Userdao.checkUser(user);
       
    }
    
}
