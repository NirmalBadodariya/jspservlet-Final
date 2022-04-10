package service;

import model.UserBean;
import serviceinterface.ForgotPassInterface;
public class ForgotPassService implements ForgotPassInterface{
    private dao.Userdao Userdao;
    public boolean checkUserDetailsForForgotPass(UserBean checkForgotpassDetails) {
        Userdao = new dao.Userdao();
        return Userdao.checkUserDetailsForForgotPass(checkForgotpassDetails);
        
    }
    
}
