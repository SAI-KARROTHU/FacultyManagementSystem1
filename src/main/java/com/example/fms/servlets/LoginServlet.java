package com.example.fms.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    // Hardcoded credentials
    private static final String VALID_STAFF_ID = "18";
    private static final String VALID_PASSWORD = "18";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String staffId = request.getParameter("staffId");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        // Validate against hardcoded credentials
        if (VALID_STAFF_ID.equals(staffId) && VALID_PASSWORD.equals(password)) {
            // Successful login - Create Session
            HttpSession session = request.getSession();
            session.setAttribute("staffId", staffId);
            session.setAttribute("name", "Admin User");
            session.setAttribute("email", "admin@faculty.edu");
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            // Create Cookie if "Remember Me" is checked
            if (rememberMe != null && rememberMe.equals("on")) {
                Cookie staffIdCookie = new Cookie("staffId", staffId);
                staffIdCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                staffIdCookie.setPath("/");
                response.addCookie(staffIdCookie);
                
                Cookie nameCookie = new Cookie("name", "Admin User");
                nameCookie.setMaxAge(7 * 24 * 60 * 60);
                nameCookie.setPath("/");
                response.addCookie(nameCookie);
            }
            
            response.sendRedirect("home.jsp");
        } else {
            // Failed login
            request.setAttribute("error", "Invalid Staff ID or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}