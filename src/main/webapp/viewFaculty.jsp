
<!-- ============================================================ -->
<!-- 5. viewFaculty.jsp - View All Faculty with Edit/Delete -->
<!-- ============================================================ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.fms.models.Faculty" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Faculty - FMS</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="navigation.jsp" %>
    
    <div class="container">
        <h1>Faculty List</h1>
        
        <% 
            String success = request.getParameter("success");
            String error = request.getParameter("error");
            
            if ("updated".equals(success)) {
        %>
            <div class="alert alert-success">Faculty updated successfully!</div>
        <% } else if ("deleted".equals(success)) { %>
            <div class="alert alert-success">Faculty deleted successfully!</div>
        <% } else if (error != null) { %>
            <div class="alert alert-error">Error: <%= error %></div>
        <% } %>
        
        <% 
            List<Faculty> facultyList = (List<Faculty>) request.getAttribute("facultyList");
            
            if (facultyList != null && !facultyList.isEmpty()) {
        %>
        
        <div class="table-container">
            <table class="faculty-table">
                <thead>
                    <tr>
                        <th>Staff ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Department</th>
                        <th>Designation</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Faculty faculty : facultyList) { %>
                    <tr>
                        <td><%= faculty.getStaffId() %></td>
                        <td><%= faculty.getName() %></td>
                        <td><%= faculty.getEmail() != null ? faculty.getEmail() : "-" %></td>
                        <td><%= faculty.getPhone() != null ? faculty.getPhone() : "-" %></td>
                        <td><%= faculty.getDepartmentName() != null ? faculty.getDepartmentName() : "-" %></td>
                        <td><%= faculty.getDesignation() != null ? faculty.getDesignation() : "-" %></td>
                        <td class="action-buttons">
                            <a href="updateFaculty?id=<%= faculty.getFacultyId() %>" 
                               class="btn btn-edit">Edit</a>
                            
                            <form action="deleteFaculty" method="post" style="display:inline;" 
                                  onsubmit="return confirm('Are you sure you want to delete this faculty member?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="facultyId" value="<%= faculty.getFacultyId() %>">
                                <button type="submit" class="btn btn-delete">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        
        <% } else { %>
            <div class="alert alert-info">No faculty records found.</div>
        <% } %>
        
        <div class="form-actions">
            <a href="addFaculty" class="btn btn-primary">Add New Faculty</a>
            <a href="home.jsp" class="btn btn-secondary">Back to Home</a>
        </div>
    </div>
</body>
</html>