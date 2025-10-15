// ============================================================
// 3. AddFacultyServlet.java - Using Hidden Form Fields
// ============================================================
package com.example.fms.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AddFacultyServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FacultyDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to add faculty page
        request.getRequestDispatcher("addFaculty.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the action from hidden field
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addFaculty(request, response);
        } else {
            response.sendRedirect("addFaculty.jsp");
        }
    }
    
    private void addFaculty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String staffId = request.getParameter("staffId");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String hireDate = request.getParameter("hireDate");
        String departmentId = request.getParameter("departmentId");
        String designation = request.getParameter("designation");
        String researchArea = request.getParameter("researchArea");
        String qualifications = request.getParameter("qualifications");
        
        // Get session info for audit
        HttpSession session = request.getSession();
        String addedBy = (String) session.getAttribute("staffId");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "INSERT INTO Faculty (staff_id, name, date_of_birth, gender, email, " +
                        "phone_number, address, hire_date, department_id, designation, " +
                        "research_area, qualifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            pstmt.setString(2, name);
            pstmt.setString(3, dob);
            pstmt.setString(4, gender);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setString(7, address);
            pstmt.setString(8, hireDate);
            pstmt.setInt(9, Integer.parseInt(departmentId));
            pstmt.setString(10, designation);
            pstmt.setString(11, researchArea);
            pstmt.setString(12, qualifications);
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                request.setAttribute("success", "Faculty added successfully!");
            } else {
                request.setAttribute("error", "Failed to add faculty.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        request.getRequestDispatcher("addFaculty.jsp").forward(request, response);
    }
}
