<!-- 2. navigation.jsp - Navigation Bar with Session Info -->
<!-- ============================================================ -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String staffId = (String) session.getAttribute("staffId");
    String name = (String) session.getAttribute("name");
    
    // Check cookies if session is null
    if (staffId == null) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("staffId")) {
                    staffId = cookie.getValue();
                } else if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                }
            }
        }
    }
%>

<nav class="navbar">
    <div class="nav-container">
        <div class="nav-brand">
            <h2>Faculty Management System</h2>
        </div>
        
        <ul class="nav-menu">
            <li><a href="home.jsp">Home</a></li>
            <li><a href="addFaculty">Add Faculty</a></li>
            <li><a href="viewFaculty">View Faculty</a></li>
            <li class="nav-user">
                Welcome, <%= name != null ? name : staffId %>
            </li>
            <li><a href="logout" class="btn-logout">Logout</a></li>
        </ul>
    </div>
</nav>
