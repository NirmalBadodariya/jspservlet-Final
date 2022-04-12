package service;

import dao.Userdao;
import model.UserBean;

public class GetUserDetails {
    Userdao Userdao = new Userdao();
    public UserBean getUserDetails(String email) {
        return Userdao.getUserDetails(email);
    }
    
}
