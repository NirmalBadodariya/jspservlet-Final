package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/home.jsp", "/adminHome1.jsp" })
public class SessionFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws java.io.IOException, ServletException {
        System.out.println("Filter called for remove");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String mail = (String) session.getAttribute("email");

        if (mail.equals("")) {
            response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
                                                             // circumstance
            response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
                                                             // origin server
            response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
            request.setAttribute("Error", "Session has ended.  Please login.");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
