package daoInterface;

import model.UserBean;

public interface UserdaoInterface {
        public static final String insert_users = "INSERT INTO users"
                        + "  (firstname,lastname,email,phone,gender,dob,pass,security_ans,user_profile) VALUES "
                        + " (?, ?, ?, ?, ?,?,?,?,?);";
        public static final String login_credentials = "select users.email , users.pass , assignned_roles.u_id , assignned_roles.role FROM users INNER JOIN assignned_roles ON users.id = assignned_roles.u_id;";

        public static final String insert_addresses = "INSERT INTO user_addresses"
                        + "(user_id,address_line1,address_line2,city,pincode,state) VALUES" + " (?, ?, ?, ?, ?,?);";

        public static final String checkforgotPassDetails = "select * from users";
        public static final String changePass = "update users set pass=? where dob=? and security_ans=?";
        public static final String setusertype = "INSERT INTO assignned_roles" +
                        "(u_id,role) VALUES" + "(?,?);";

        public static final String update_user = "UPDATE  users set firstname=?,lastname=?,email=?,phone=?,gender=?,dob=?,pass=?,security_ans=?,user_profile=? where id=?";

        public static final String update_addresses = "update  user_addresses set address_line1=?,address_line2=?,city=?,pincode=?,state=? where user_id=? and id=?";               

        public String insertUser(UserBean user);

        public String checkUser(UserBean user);
}
