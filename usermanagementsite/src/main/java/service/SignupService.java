package service;
import model.UserBean;
import serviceinterface.SignupServiceInterface;
public class SignupService implements SignupServiceInterface {
    public String insertUser(UserBean newUser) {
         final dao.Userdao Userdao;
        Userdao = new dao.Userdao();
       String id =  Userdao.insertUser(newUser);
        return id;
    }

}
