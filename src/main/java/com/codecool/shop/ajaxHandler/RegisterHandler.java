package com.codecool.shop.ajaxHandler;

import com.codecool.shop.bcrypt.BCrypt;
import com.codecool.shop.orderData.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register-handler"})
public class RegisterHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt());
        String email = req.getParameter("email");
        String billingAddress = req.getParameter("billing");
        String shippingAddress = req.getParameter("shipping");
        Customer customer = new Customer(username, email, billingAddress, shippingAddress);
        customer.setPassword(password);
        UsersDaoJdbc.getInstance().register(customer);
    }
}
