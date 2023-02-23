package ibf2022.paf.day29exercise.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private String orderId;
    private String name;
    private String email;
    private Date deliveryDate;
    public List<LineItem> lineItems = new LinkedList<>();

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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString(){
        return "Order [ame=" + name + ", email=" + email + ", deliveryDate=" 
			  + deliveryDate + ", lineItems=" + lineItems + "]";
    }

   
}
