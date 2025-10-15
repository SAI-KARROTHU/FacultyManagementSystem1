<!-- 4. addFaculty.jsp - Add Faculty with Hidden Form Fields -->
<!-- ============================================================ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Faculty - FMS</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="navigation.jsp" %>
    
    <div class="container">
        <h1>Add New Faculty</h1>
        
        <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="addFaculty" method="post" class="faculty-form">
            <!-- Hidden field for action -->
            <input type="hidden" name="action" value="add">
            
            <!-- Hidden field to track who added this (from session) -->
            <input type="hidden" name="addedBy" value="<%= session.getAttribute("staffId") %>">
            
            <div class="form-row">
                <div class="form-group">
                    <label for="staffId">Staff ID: *</label>
                    <input type="text" id="staffId" name="staffId" required>
                </div>
                
                <div class="form-group">
                    <label for="name">Full Name: *</label>
                    <input type="text" id="name" name="name" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="dob" name="dob">
                </div>
                
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender">
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email">
                </div>
                
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="tel" id="phone" name="phone">
                </div>
            </div>
            
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="3"></textarea>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="hireDate">Hire Date:</label>
                    <input type="date" id="hireDate" name="hireDate">
                </div>
                
                <div class="form-group">
                    <label for="departmentId">Department ID:</label>
                    <input type="number" id="departmentId" name="departmentId">
                </div>
            </div>
            
            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation">
            </div>
            
            <div class="form-group">
                <label for="researchArea">Research Area:</label>
                <input type="text" id="researchArea" name="researchArea">
            </div>
            
            <div class="form-group">
                <label for="qualifications">Qualifications:</label>
                <textarea id="qualifications" name="qualifications" rows="3"></textarea>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Add Faculty</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
                <a href="home.jsp" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
