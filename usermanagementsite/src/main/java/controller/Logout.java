package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("email");
        String mail = (String) session.getAttribute("email");

        response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
                                                         // origin server
        response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility

        if (null == mail) {
            request.setAttribute("Error", "Session has ended.  Please login.");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

}
