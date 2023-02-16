package ibf2022.paf.day29exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import ibf2022.paf.day29exercise.model.Order;

import java.util.UUID;

@Service
public class OrderService {
    
    public void insertOrder(Order order) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);
    }

    // public void insertOrder (Order order) {
    //     Document doc = order.toDocument();
    //     template.insert(doc, COLLECTION_ORDER);
    // }

}
