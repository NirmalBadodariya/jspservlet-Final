package service;

public class DeleteUserService {
    private dao.AdminDao AdminDao;
    public void deleteUser(int userId) {
        AdminDao = new dao.AdminDao();
        AdminDao.deleteUser(userId);
    }
    
}
