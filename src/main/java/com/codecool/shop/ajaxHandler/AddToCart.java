package com.codecool.shop.ajaxHandler;

import com.codecool.shop.dao.implementation.jdbc.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.orderData.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = ProductDaoJdbc.getInstance().find(productId);

        Order.getInstance().addToCartList(product);
    }
}
