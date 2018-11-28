package com.codecool.shop.orderData;

import com.codecool.shop.dao.implementation.jdbc.OrderDaoJdbc;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private List<LineItem> cartList = new ArrayList<>();
    private double total;
    private Customer customer;
    private Date date;
    private static Order instance = null;
    private int id;

    private Order() {
        this.date = new Date();
        OrderDaoJdbc orderDaoJdbc = OrderDaoJdbc.getInstance();
        this.id = orderDaoJdbc.getNextOrderId();
    }

    public void deleteOrder() {
        instance = null;
    }

    public static Order getInstance() {
        if(instance == null)
            instance = new Order();
        return instance;
    }

    public List<LineItem> getCartList() {
        return cartList;
    }

    public void addToCartList(Product product){
        for (LineItem lineItem : cartList) {
            if (lineItem.compareProductId(product.getId())) {
                lineItem.setQuantity();
                setTotal();
                return;
            }
        }
        cartList.add(new LineItem(product));
        setTotal();
    }

    public void removeFromCartList(Product product){
        List<LineItem> toRemove = new ArrayList<>();

        for(LineItem item : cartList){
            if(item.compareProductId(product.getId())){
                toRemove.add(item);
            }
        }
        cartList.removeAll(toRemove);
        setTotal();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTotal() {
//        this.total = cartList.stream().map(product -> product.getSubTotalPrice()).reduce((x, y) -> x + y).get();

        double tempTotal = 0;
        for(LineItem item : cartList){
            tempTotal += item.getSubTotalPrice();
        }
        this.total = tempTotal;

    }

    public int getNumberOfProducts() {
        int numOfProducts = 0;
        if (cartList.size() != 0) {
            for (LineItem lineItem: cartList) {
                numOfProducts += lineItem.getQuantity();
            }
        }
        return numOfProducts;
    }

    public double getTotal() {
        return total;
    }


    // handle minus!!!
    public void changeQuantity(Product product, int quantity) {
        if (quantity > 0) {
            for (LineItem item : cartList) {
                if (item.compareProductId(product.getId())) {
                    item.setQuantity(quantity);
                    setTotal();
                }
            }
        } else if (quantity == 0) {
            removeFromCartList(product);
            setTotal();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order: ");
        for (LineItem item : cartList) {
            sb.append(item.toString());
        }
        return sb.toString();
    }
}
