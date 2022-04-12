package serviceinterface;

import java.util.ArrayList;

import model.AddressBean;
import model.UserBean;

public interface UserDataManipultionServiceInterface {
     void updateaddress(ArrayList<AddressBean> addresses, int hiddenId);
     void addnewAddress(ArrayList<AddressBean> newAddresses, int hiddenId);
     void updateNewUserDetails(UserBean newUser);
}
