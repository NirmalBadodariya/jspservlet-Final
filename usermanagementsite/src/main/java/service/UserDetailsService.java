package service;

import java.util.ArrayList;

import dao.AdminDao;
import model.UserBean;
public class UserDetailsService {

    private AdminDao AdminDao;

    public ArrayList<UserBean> getUserDetails() {

        AdminDao = new AdminDao();
        ArrayList<UserBean> userDetails = AdminDao.getUserDetails();
        return userDetails;
    }
    
}
