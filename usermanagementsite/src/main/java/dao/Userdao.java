package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import daoInterface.UserdaoInterface;
import model.AddressBean;
import model.ForgotPassBean;
import model.UserBean;

public class Userdao extends DBConnection implements UserdaoInterface {
    Logger log = Logger.getLogger(Userdao.class.getName());

    public void init() {

        BasicConfigurator.configure();

    }

    public String insertUser(UserBean user) {
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                // adding data to users table.
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
                String id = res.getString(1);

                // storing usertype in roles table.
                PreparedStatement ps = connection.prepareStatement(setusertype);
                ps.setString(1, id);
                ps.setInt(2, 1);
                ps.executeUpdate();

                // adding data to addresses table
                for (int i = 0; i < user.getAddresses().size(); i++) {

                    PreparedStatement preparedStatement1 = connection.prepareStatement(insert_addresses);
                    preparedStatement1.setString(1, id);

                    preparedStatement1.setString(2, user.getAddresses().get(i).getAddressLine1());
                    preparedStatement1.setString(3, user.getAddresses().get(i).getAddressLine2());
                    preparedStatement1.setString(4, user.getAddresses().get(i).getCity());
                    preparedStatement1.setInt(5, user.getAddresses().get(i).getPincode());
                    preparedStatement1.setString(6, user.getAddresses().get(i).getState());
                    preparedStatement1.executeUpdate();
                }
                return id;

            } else {
                log.error("Connection Not Established");
            }

        } catch (Exception e) {
            log.error("Exception:" + e);
        }
        return null;
    }

    // Checking userdata for logging in.
    public String checkUser(UserBean user) {
        String usertype = "";
        try {
            Connection connection = getDBConnection();
            if (connection != null) {

                PreparedStatement preparedStatement = connection.prepareStatement(login_credentials);

                ResultSet rs = preparedStatement.executeQuery();
                // loop through database to find matching email and pass
                while (rs.next()) {

                    if (rs.getString("email").equals(user.getEmail()) && rs.getString("pass").equals(user.getPass())
                            && rs.getString(4).equals("1")) {
                        // usertype defines type of user 1 for user.
                        usertype = "User";
                                
                        break;
                    } else if (rs.getString("email").equals(user.getEmail())
                            && rs.getString("pass").equals(user.getPass()) && rs.getString(4).equals("2")) {
                        // usertype defines type of user 2 for admin.
                        usertype = "Admin";

                        break;
                    }
                }

            }

        } catch (Exception e) {
            log.error("Exception:" + e);
        }
        return usertype;

    }

    // again checking details of users if he has forgot his pass
    // details we are checking here are DOB and security ans from user into database
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
                log.error("Connection Not Established");
            }

        } catch (Exception e) {
            log.error("Exception:" + e);
        }
        return check;
    }

    // updating password of user if he has forgotten it.
    public void setNewPass(ForgotPassBean forgotPass) {
        try {

            Connection connection = getDBConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(changePass);
                preparedStatement.setString(1, forgotPass.getNewPass());
                preparedStatement.setString(2, forgotPass.getDob());
                preparedStatement.setString(3, forgotPass.getSecurityAns());
                preparedStatement.executeUpdate();

            } else {
                log.error("Connection Not Established");
            }

        } catch (Exception e) {
            log.error("Exception :" + e);

        }
    }

    // Getting UserDetails to show to user for editing.
    public UserBean getUserDetails(String email) {
        
        ArrayList<AddressBean> addresses = new ArrayList<>();
        try {
            
            Connection connection = getDBConnection();
            if (connection != null) {
                String query = "select * from users  where email=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                UserBean user = new UserBean();
                rs.next();
                int id = rs.getInt(1);
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setGender(rs.getString(6));
                user.setDob(rs.getString(7));
                user.setPass(rs.getString(8));
                user.setSecurityAns(rs.getString(9));

                

                String query1 = "select * from user_addresses  where user_id=?";
                PreparedStatement ps1 = connection.prepareStatement(query1);
                ps1.setInt(1, id);
                ResultSet rs1 = ps1.executeQuery();
                AddressBean addressesOfUsers = new AddressBean();

                while (rs1.next()) {
                    addressesOfUsers.setAddressLine1(rs1.getString(3));
                    addressesOfUsers.setAddressLine2(rs1.getString(4));
                    addressesOfUsers.setCity(rs1.getString(5));
                    addressesOfUsers.setPincode(rs1.getInt(6));
                    addressesOfUsers.setState(rs1.getString(7));
                    addresses.add(addressesOfUsers);
                }
                user.setAddresses(addresses);
                
                return user;
            } else {
                log.error("Connection is null");
            }
        } catch (Exception e) {
            log.error("Exception:" + e);

        }
        return null;

    }

}
