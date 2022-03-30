package daoInterface;

public interface AdminDaoInterface {
    public static final String userDetailsQuery = "select * from users";
    // public static final String deleteUserQUery = "DELETE FROM users, user_addresses USING users INNER JOIN user_addresses  WHERE id = ? AND user_addresses.user_id = users.id";
}
