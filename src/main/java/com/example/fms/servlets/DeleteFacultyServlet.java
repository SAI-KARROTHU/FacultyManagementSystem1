package com.example.fms.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFacultyServlet")
public class DeleteFacultyServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FacultyDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String facultyId = request.getParameter("facultyId");
        
        if (!"delete".equals(action) || facultyId == null) {
            response.sendRedirect("viewFaculty?error=invalidrequest");
            return;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "DELETE FROM Faculty WHERE faculty_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(facultyId));
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                response.sendRedirect("viewFaculty?success=deleted");
            } else {
                response.sendRedirect("viewFaculty?error=deletefailed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewFaculty?error=" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("viewFaculty");
    }
}