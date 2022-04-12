package daoInterface;

public interface AdminDaoInterface {
    // public static final String userDetailsQuery1 = "select * from users ";
     String userDetailsQuery = "select  users.id, users.firstname , users.email from users inner join assignned_roles on users.id = assignned_roles.u_id where assignned_roles.role=1; ";
    // public static final String deleteUserQUery = "DELETE FROM users, user_addresses USING users INNER JOIN user_addresses  WHERE id = ? AND user_addresses.user_id = users.id";
}
