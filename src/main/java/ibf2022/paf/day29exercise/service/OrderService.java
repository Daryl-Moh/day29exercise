package ibf2022.paf.day29exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.paf.day29exercise.model.Order;
import ibf2022.paf.day29exercise.repository.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public String insertOrder(Order order) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);

        // Save to mongo
        orderRepo.insertOrder(order);

        return orderId;
    }
}
