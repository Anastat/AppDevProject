package com.servlet.session;

import Source.Users;
import Source.service.UsersFacadeREST;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    @PersistenceContext(unitName = "sokoshotelPU")
    private EntityManager em;
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        
        // get request parameters for username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Users> results = em.createNamedQuery("Users.findByUsername", Users.class).setParameter("username", username).getResultList();
        if (!results.isEmpty() && username.equals(results.get(0).getUsername())) {
            if (results.get(0).getPassword().equals(password)) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                Cookie secret = new Cookie("supersecret", "secret");
                Cookie message = new Cookie("username", username);
                response.addCookie(message);
                response.addCookie(secret);
                response.sendRedirect("mainpage.html");           
            }else {
                response.sendRedirect("http://10.114.32.74:8080/sokoshotel/?dc=wrong");
            }
            
        } else {
            response.sendRedirect("http://10.114.32.74:8080/sokoshotel/?dc=wrong");
        }
    }
} 