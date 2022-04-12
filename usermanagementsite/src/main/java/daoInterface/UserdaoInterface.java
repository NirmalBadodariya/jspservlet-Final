package daoInterface;

import model.UserBean;

public interface UserdaoInterface {
        String insert_users = "INSERT INTO users"
                                + "  (firstname,lastname,email,phone,gender,dob,pass,security_ans,user_profile) VALUES "
                                + " (?, ?, ?, ?, ?,?,?,?,?);";
        String login_credentials = "select users.email , users.pass , assignned_roles.u_id , assignned_roles.role FROM users INNER JOIN assignned_roles ON users.id = assignned_roles.u_id;";

        String insert_addresses = "INSERT INTO user_addresses"
                                + "(user_id,address_line1,address_line2,city,pincode,state) VALUES" + " (?, ?, ?, ?, ?,?);";

        String checkforgotPassDetails = "select * from users";
        String changePass = "update users set pass=? where dob=? and security_ans=?";
        String setusertype = "INSERT INTO assignned_roles" +
                                "(u_id,role) VALUES" + "(?,?);";

        String update_user = "UPDATE  users set firstname=?,lastname=?,email=?,phone=?,gender=?,dob=?,pass=?,security_ans=?,user_profile=? where id=?";

        String update_addresses = "update  user_addresses set address_line1=?,address_line2=?,city=?,pincode=?,state=? where user_id=? and id=?";               

        String insertUser(UserBean user);

        String checkUser(UserBean user);
}
