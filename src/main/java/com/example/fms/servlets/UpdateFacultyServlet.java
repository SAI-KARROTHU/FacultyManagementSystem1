package com.example.fms.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateFacultyServlet")
public class UpdateFacultyServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FacultyDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        if (id == null || id.isEmpty()) {
            response.sendRedirect("viewFaculty");
            return;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "SELECT * FROM Faculty WHERE faculty_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                request.setAttribute("facultyId", rs.getInt("faculty_id"));
                request.setAttribute("staffId", rs.getString("staff_id"));
                request.setAttribute("name", rs.getString("name"));
                request.setAttribute("dob", rs.getString("date_of_birth"));
                request.setAttribute("gender", rs.getString("gender"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("phone", rs.getString("phone_number"));
                request.setAttribute("address", rs.getString("address"));
                request.setAttribute("hireDate", rs.getString("hire_date"));
                request.setAttribute("departmentId", rs.getInt("department_id"));
                request.setAttribute("designation", rs.getString("designation"));
                request.setAttribute("researchArea", rs.getString("research_area"));
                request.setAttribute("qualifications", rs.getString("qualifications"));
                
                request.getRequestDispatcher("UpdateFaculty.jsp").forward(request, response);
            } else {
                response.sendRedirect("viewFaculty?error=notfound");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewFaculty?error=" + e.getMessage());
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (!"update".equals(action)) {
            response.sendRedirect("viewFaculty");
            return;
        }
        
        String facultyId = request.getParameter("facultyId");
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
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "UPDATE Faculty SET staff_id=?, name=?, date_of_birth=?, gender=?, " +
                        "email=?, phone_number=?, address=?, hire_date=?, department_id=?, " +
                        "designation=?, research_area=?, qualifications=? WHERE faculty_id=?";
            
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
            pstmt.setInt(13, Integer.parseInt(facultyId));
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                response.sendRedirect("viewFaculty?success=updated");
            } else {
                response.sendRedirect("viewFaculty?error=updatefailed");
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
}