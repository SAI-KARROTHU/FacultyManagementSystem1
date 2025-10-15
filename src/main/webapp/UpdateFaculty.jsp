<!-- 6. updateFaculty.jsp - Update Faculty with Hidden Fields -->
<!-- ============================================================ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Faculty - FMS</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="navigation.jsp" %>
    
    <div class="container">
        <h1>Update Faculty Information</h1>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="updateFaculty" method="post" class="faculty-form">
            <!-- Hidden fields for action and faculty ID -->
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="facultyId" value="<%= request.getAttribute("facultyId") %>">
            
            <!-- Hidden field to track who updated this -->
            <input type="hidden" name="updatedBy" value="<%= session.getAttribute("staffId") %>">
            
            <div class="form-row">
                <div class="form-group">
                    <label for="staffId">Staff ID: *</label>
                    <input type="text" id="staffId" name="staffId" 
                           value="<%= request.getAttribute("staffId") %>" required>
                </div>
                
                <div class="form-group">
                    <label for="name">Full Name: *</label>
                    <input type="text" id="name" name="name" 
                           value="<%= request.getAttribute("name") %>" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="dob" name="dob" 
                           value="<%= request.getAttribute("dob") != null ? request.getAttribute("dob") : "" %>">
                </div>
                
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender">
                        <option value="">Select Gender</option>
                        <option value="Male" <%= "Male".equals(request.getAttribute("gender")) ? "selected" : "" %>>Male</option>
                        <option value="Female" <%= "Female".equals(request.getAttribute("gender")) ? "selected" : "" %>>Female</option>
                        <option value="Other" <%= "Other".equals(request.getAttribute("gender")) ? "selected" : "" %>>Other</option>
                    </select>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" 
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>">
                </div>
                
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="tel" id="phone" name="phone" 
                           value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>">
                </div>
            </div>
            
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="3"><%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %></textarea>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="hireDate">Hire Date:</label>
                    <input type="date" id="hireDate" name="hireDate" 
                           value="<%= request.getAttribute("hireDate") != null ? request.getAttribute("hireDate") : "" %>">
                </div>
                
                <div class="form-group">
                    <label for="departmentId">Department ID:</label>
                    <input type="number" id="departmentId" name="departmentId" 
                           value="<%= request.getAttribute("departmentId") != null ? request.getAttribute("departmentId") : "" %>">
                </div>
            </div>
            
            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation" 
                       value="<%= request.getAttribute("designation") != null ? request.getAttribute("designation") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="researchArea">Research Area:</label>
                <input type="text" id="researchArea" name="researchArea" 
                       value="<%= request.getAttribute("researchArea") != null ? request.getAttribute("researchArea") : "" %>">
            </div>
            
            <div class="form-group">
                <label for="qualifications">Qualifications:</label>
                <textarea id="qualifications" name="qualifications" rows="3"><%= request.getAttribute("qualifications") != null ? request.getAttribute("qualifications") : "" %></textarea>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update Faculty</button>
                <a href="viewFaculty" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>