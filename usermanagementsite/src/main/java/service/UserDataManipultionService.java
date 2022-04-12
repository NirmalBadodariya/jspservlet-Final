package service;

import java.util.ArrayList;

import dao.Userdao;
import model.AddressBean;
import model.UserBean;
import serviceinterface.UserDataManipultionServiceInterface;

public class UserDataManipultionService implements UserDataManipultionServiceInterface {
    Userdao Userdao = new Userdao();
    
	public void updateNewUserDetails(UserBean newUser) {
        Userdao.updateNewUserDetails(newUser);
	}

    public void addnewAddress(ArrayList<AddressBean> newAddresses, int hiddenId) {
        Userdao.addnewAddress(newAddresses, hiddenId);
    }

    public void updateaddress(ArrayList<AddressBean> addresses, int hiddenId) {
        Userdao.updateaddress(addresses, hiddenId);
    }
    
}
