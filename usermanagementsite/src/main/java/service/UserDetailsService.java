package service;

import java.util.ArrayList;

import dao.AdminDao;
import model.UserBean;

public class UserDetailsService {

    private dao.AdminDao AdminDao;

    public ArrayList<UserBean> getUserDetails() {

        AdminDao = new dao.AdminDao();
        ArrayList<UserBean> userDetails = AdminDao.getUserDetails();
        return userDetails;
    }

}
