package daoInterface;

import model.UserBean;

public interface UserdaoInterface {
    public static final String insert_users = "INSERT INTO users"
            + "  (firstname,lastname,email,phone,gender,dob,pass,security_ans,user_profile) VALUES "
            + " (?, ?, ?, ?, ?,?,?,?,?);";
    public static final String login_credentials = "select * from users";
        
    public static final String insert_addresses = "INSERT INTO user_addresses"
            + "(user_id,address_line1,address_line2,city,pincode,state) VALUES" + " (?, ?, ?, ?, ?,?);";

    public static final String checkforgotPassDetails = "select * from users";
    public static final String changePass = "update users set pass=? where dob=? and security_ans=?";

    public void insertUser(UserBean user);

    public boolean checkUser(UserBean user);
}
