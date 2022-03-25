package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private dao.Userdao Userdao;

    public void init() {
        Userdao = new dao.Userdao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String newPass = request.getParameter("newPass");
        HttpSession session = request.getSession();
        session.getAttribute("dob");
        session.getAttribute("securityAns");
        Userdao.setNewPass(newPass);
                
        response.sendRedirect("index.jsp");

    }
}
