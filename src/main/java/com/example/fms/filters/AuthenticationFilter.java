// ============================================================
// 2. AuthenticationFilter.java - Session & Cookie Validation
// ============================================================
package com.example.fms.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class AuthenticationFilter implements Filter {
    
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        
        // Allow access to login page and static resources
        if (uri.endsWith("login.jsp") || uri.endsWith("/login") || 
        	    uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check session
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("staffId") != null);
        
        // If no session, check for cookies
        if (!isLoggedIn) {
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                String staffId = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("staffId")) {
                        staffId = cookie.getValue();
                        break;
                    }
                }
                
                if (staffId != null) {
                    // Auto-login from cookie
                    HttpSession newSession = httpRequest.getSession();
                    newSession.setAttribute("staffId", staffId);
                    newSession.setMaxInactiveInterval(30 * 60);
                    httpRequest.changeSessionId();
                    isLoggedIn = true;
                }
            }
        }
        
        if (isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("login.html");
        }
    }
    
    public void destroy() {
        // Cleanup logic if needed
    }
}
