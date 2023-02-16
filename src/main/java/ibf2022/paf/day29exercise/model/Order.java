package ibf2022.paf.day29exercise.model;

import java.util.Date;
import java.util.List;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private Date deliverDate;
    public List<LineItem> items;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDeliverDate() {
        return deliverDate;
    }
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    public List<LineItem> getItems() {
        return items;
    }
    public void setItems(List<LineItem> items) {
        this.items = items;
    }



}
