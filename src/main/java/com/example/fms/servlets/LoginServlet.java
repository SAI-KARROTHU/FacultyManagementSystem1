package com.example.fms.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FacultyDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String staffId = request.getParameter("staffId");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Validate: username and password must both equal staff_id
            String sql = "SELECT * FROM Faculty WHERE staff_id = ? AND staff_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Successful login - Create Session
                HttpSession session = request.getSession();
                session.setAttribute("staffId", staffId);
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("email", rs.getString("email"));
                session.setMaxInactiveInterval(30 * 60); // 30 minutes
                
                // Create Cookie if "Remember Me" is checked
                if (rememberMe != null && rememberMe.equals("on")) {
                    Cookie staffIdCookie = new Cookie("staffId", staffId);
                    staffIdCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                    staffIdCookie.setPath("/");
                    response.addCookie(staffIdCookie);
                    
                    Cookie nameCookie = new Cookie("name", rs.getString("name"));
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
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("login.html").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}