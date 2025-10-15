package com.example.fms.servlets;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.fms.models.Faculty;

@WebServlet("/ViewFacultyServlet")
public class ViewFacultyServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FacultyDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List facultyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "SELECT f.*, d.department_name FROM Faculty f " +
                        "LEFT JOIN Department d ON f.department_id = d.department_id " +
                        "ORDER BY f.staff_id";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Faculty faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("faculty_id"));
                faculty.setStaffId(rs.getString("staff_id"));
                faculty.setName(rs.getString("name"));
                faculty.setDob(rs.getString("date_of_birth"));
                faculty.setGender(rs.getString("gender"));
                faculty.setEmail(rs.getString("email"));
                faculty.setPhone(rs.getString("phone_number"));
                faculty.setAddress(rs.getString("address"));
                faculty.setHireDate(rs.getString("hire_date"));
                faculty.setDepartmentId(rs.getInt("department_id"));
                faculty.setDepartmentName(rs.getString("department_name"));
                faculty.setDesignation(rs.getString("designation"));
                faculty.setResearchArea(rs.getString("research_area"));
                faculty.setQualifications(rs.getString("qualifications"));
                
                facultyList.add(faculty);
            }
            
            request.setAttribute("facultyList", facultyList);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        request.getRequestDispatcher("viewFaculty.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
