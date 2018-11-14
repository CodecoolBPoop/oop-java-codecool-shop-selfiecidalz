package com.codecool.shop.orderData;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void sendEmail(JSONObject orderInfo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("selfiecidalz","s3lf13c1d4lz");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@no-spam.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(orderInfo.getJSONObject("customer").get("email").toString()));
            message.setSubject("Order confirmation");
            StringBuilder emailMessage = new StringBuilder();

            emailMessage.append("Dear " + orderInfo.getJSONObject("customer").get("name") + "\n");
            emailMessage.append("We send you your order details: " +"\n");
            JSONArray array = orderInfo.getJSONArray("cartList");
            for(int i = 0; i < array.length(); i++){
                JSONObject item = array.getJSONObject(i);
                emailMessage.append(item.get("productname") + ",    " + item.get("quantity") + ",       " + item.get("subtotal") + "$" + "\n");
            }

            emailMessage.append("Total: " + orderInfo.get("total") + "$" + "\n");
            emailMessage.append("Szeretettel ÖLel a SelfiecidalZ csapata ;)");

            message.setText(emailMessage.toString());

            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("Something is wrong, try again!");
        }
    }
}

