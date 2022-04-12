package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import daoInterface.AdminDaoInterface;
import model.UserBean;

public class AdminDao extends DBConnection implements AdminDaoInterface {
    Logger log = Logger.getLogger(AdminDao.class.getName());

    public void init() {

        BasicConfigurator.configure();

    }

    // getting user details to show in datatable to admin.
    public ArrayList<UserBean> getUserDetails() {
        ArrayList<UserBean> userDetails = new ArrayList<>();
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {

            if (connection != null) {

                preparedStatement = connection.prepareStatement(userDetailsQuery);
                rs = preparedStatement.executeQuery();

                while (rs.next()) {

                    UserBean user = new UserBean();
                    user.setId(rs.getInt(1));
                    user.setFirstName(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    userDetails.add(user);
                }

                return userDetails;
            } else {
                log.error("Connection is null");
            }
        } catch (Exception e) {
            log.error("Exception:" + e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.error("Excs:" + e);
            }
        }
        return null;

    }

    // deleting of user by admin.
    public void deleteUser(int userId) {
        PreparedStatement preparedStatement = null;
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                String deleteUserQUery1 = "DELETE FROM users where id=" + userId;
                 preparedStatement = connection.prepareStatement(deleteUserQUery1);
                preparedStatement.executeUpdate();

            } else {
                log.error("Connection is null");
            }
        } catch (Exception e) {
            log.error("Exception:" + e);

        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                log.error("Excs:" + e);
            }
        }

    }

}
