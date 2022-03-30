package service;
import model.UserBean;
import serviceinterface.SignupServiceInterface;
public class SignupService implements SignupServiceInterface {
    private dao.Userdao Userdao;
    public String insertUser(UserBean newUser) {
        Userdao = new dao.Userdao();
       String id =  Userdao.insertUser(newUser);
        return id;
    }

}
