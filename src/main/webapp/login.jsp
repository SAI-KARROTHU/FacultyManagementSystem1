<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Faculty Management System</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h2>Faculty Management System</h2>
            <h3>Login</h3>
            
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getParameter("error") %>
                </div>
            <% } %>
            
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="staffId">Staff ID:</label>
                    <input type="text" id="staffId" name="staffId" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                    <small>Note: Password must be same as Staff ID</small>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="rememberMe"> Remember Me (7 days)
                    </label>
                </div>
                
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
</body>
</html>
