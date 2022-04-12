package service;

import model.ForgotPassBean;
import dao.Userdao;
public class ChangePassService {
    Userdao Userdao = new Userdao();
    public void setNewPass(ForgotPassBean forgotPass) {
        Userdao.setNewPass(forgotPass);
    }
    
}
