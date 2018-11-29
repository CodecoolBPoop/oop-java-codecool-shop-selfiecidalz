package com.codecool.shop.controller;


import com.codecool.shop.bcrypt.BCrypt;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.jdbc.UsersDaoJdbc;
import com.codecool.shop.orderData.Customer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Customer customer = UsersDaoJdbc.getInstance().getCostumerByUsername(username);
        String billingAddress = customer.getBillingAddress();
        String shippingAddress = customer.getShippingAddress();
        String email = customer.getEmail();
        if (BCrypt.checkpw(password, UsersDaoJdbc.getInstance().getHashByUsername(username))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("email", email);
            session.setAttribute("billing", billingAddress);
            session.setAttribute("shipping", shippingAddress);
            resp.sendRedirect("/");
        }
    }
}
