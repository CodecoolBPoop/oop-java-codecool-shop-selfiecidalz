package com.codecool.shop.ajaxHandler;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.orderData.LineItem;
import com.codecool.shop.orderData.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/refresh"})
public class RefreshQuantity extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = ProductDaoMem.getInstance().find(productId);

        Order.getInstance().changeQuantity(product, quantity);
    }
}
