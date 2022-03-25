package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import daoInterface.UserdaoInterface;
import model.UserBean;

public class Userdao extends DBConnection implements UserdaoInterface {

    public void insertUser(UserBean user) {
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(insert_users,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPhone());
                preparedStatement.setString(5, user.getGender());
                preparedStatement.setString(6, user.getDob());
                preparedStatement.setString(7, user.getPass());
                preparedStatement.setString(8, user.getSecurityAns());
                preparedStatement.setBlob(9, user.getImage());

                preparedStatement.executeUpdate();
                ResultSet res = preparedStatement.getGeneratedKeys();
                res.next();
               String a =  res.getString(1);
                System.out.println("This id: " + res.getString(1));
                PreparedStatement preparedStatement1 = connection.prepareStatement(insert_addresses);
                preparedStatement1.setString(1, a);
                preparedStatement1.setString(2, user.getALine1());
                preparedStatement1.setString(3, user.getALine2());
                preparedStatement1.setString(4, user.getCity());
                preparedStatement1.setInt(5, user.getPin());
                preparedStatement1.setString(6, user.getState());
                preparedStatement1.executeUpdate();
                    
            } else {
                System.out.println("Connection was not Esatablished");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean checkUser(UserBean user) {
        boolean check = false;
        try {
            Connection connection = getDBConnection();
            if (connection != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(login_credentials);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    if (rs.getString("email").equals(user.getEmail()) && rs.getString("pass").equals(user.getPass())) {
                        check = true;
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return check;

    }

    public boolean checkUserDetailsForForgotPass(UserBean checkForgotpassDetails) {
        boolean check = false;
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(checkforgotPassDetails);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    if (rs.getString("dob").equals(checkForgotpassDetails.getDob())
                            && rs.getString("security_ans").equals(checkForgotpassDetails.getSecurityAns())) {
                        check = true;

                        break;
                    }
                }

            } else {
                System.out.println("Connection was not Esatablished");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }

    public void setNewPass(String newPass) {
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(changePass);
                preparedStatement.setString(1, newPass);

                preparedStatement.executeUpdate();

            } else {
                System.out.println("Connection was not Established");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
