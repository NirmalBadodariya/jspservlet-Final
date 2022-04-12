package controller;

import java.io.IOException;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DeleteUserService;


@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    Logger log = Logger.getLogger(UsersDetails.class.getName());
    private static final long serialVersionUID = 1L;
    private DeleteUserService deleteUserService;

    public void init() {

        BasicConfigurator.configure();
        deleteUserService = new DeleteUserService();
        
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));

        deleteUserService.deleteUser(userId);
        
    }
}
