package com.codecool.shop.ajaxHandler;

import com.codecool.shop.orderData.LineItem;
import com.codecool.shop.orderData.Order;
import com.codecool.shop.orderData.SendEmail;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment-handler"})
public class Payment extends HttpServlet {

    private static int fileNameExtension = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject orderJson = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        JSONObject innerJson;

        for (LineItem item: Order.getInstance().getCartList()) {
            innerJson = new JSONObject();
            innerJson.put("productname", item.getProductName());
            innerJson.put("quantity", item.getQuantity());
            innerJson.put("subtotal", item.getSubTotalPrice());

            jsonArr.put(innerJson);
        }
        orderJson.put("cartList", jsonArr);

        FileWriter fileWriter = new FileWriter("D:/test/order" + fileNameExtension++);
        fileWriter.write(orderJson.toString());
        fileWriter.close();

        Order.getInstance().deleteOrder();
        // SendEmail.sendEmail();
    }
}