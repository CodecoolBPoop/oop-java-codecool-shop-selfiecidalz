/*
package com.codecool.shop.servlets;

import com.codecool.shop.controller.ProductController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import static com.codecool.shop.controller.ProductController.productCategoryDataStore;

@WebServlet(urlPatterns = {"/ajax/filter-products"})
public class AjaxProducts extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        params.put("actual_category", productCategoryDataStore.find(id));
        res.getWriter().write("clicked to this item!");

    }
}


