<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie, java.net.URLDecoder, java.util.Date" %>
<%
    // Check if session exists, redirect to login if not
    if (session.getAttribute("staffId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Faculty Management System</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="navigation.jsp" %>
    
    <div class="container">
        <div class="dashboard">
            <h1>Welcome to Faculty Management System</h1>
            
            <div class="session-info">
                <h3>Session Information</h3>
                <p><strong>Staff ID:</strong> <%= session.getAttribute("staffId") %></p>
                <p><strong>Name:</strong> <%= session.getAttribute("name") %></p>
                <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
                <p><strong>Session ID:</strong> <%= session.getId() %></p>
                <p><strong>Session Created:</strong> <%= new Date(session.getCreationTime()) %></p>
                <p><strong>Last Accessed:</strong> <%= new Date(session.getLastAccessedTime()) %></p>
            </div>
            
            <div class="dashboard-cards">
                <div class="card">
                    <h3>Add Faculty</h3>
                    <p>Add new faculty members to the system</p>
                    <a href="addFaculty" class="btn btn-primary">Go to Add Faculty</a>
                </div>
                
                <div class="card">
                    <h3>View Faculty</h3>
                    <p>View and manage all faculty records</p>
                    <a href="viewFaculty" class="btn btn-primary">Go to View Faculty</a>
                </div>
            </div>
            
            <%
                // Display cookie information safely
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    boolean hasCookies = false;
                    for (Cookie cookie : cookies) {
                        if ("staffId".equals(cookie.getName()) || "name".equals(cookie.getName())) {
                            hasCookies = true;
                            break;
                        }
                    }
                    
                    if (hasCookies) {
            %>
                        <div class="cookie-info">
                            <h3>Cookie Information (Remember Me Active)</h3>
                            <%
                                for (Cookie cookie : cookies) {
                                    if ("staffId".equals(cookie.getName()) || "name".equals(cookie.getName())) {
                                        String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                            %>
                                        <p><strong><%= cookie.getName() %>:</strong> <%= value %></p>
                            <%
                                    }
                                }
                            %>
                        </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
