package ibf2022.paf.day29exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;

import ibf2022.paf.day29exercise.model.Order;
import ibf2022.paf.day29exercise.service.OrderService;
import ibf2022.paf.day29exercise.service.Utils;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody String payload) {
        System.out.println("Payload >>>" + payload.toString());

        // from JSON (data interchange format) -> Order (domain entity)
        JsonObject json = Utils.toJson(payload);
        Order order = Utils.toOrder(json);

        String orderId =orderService.insertOrder(order);

        JsonObject resp = Json.createObjectBuilder()
            .add("orderId", orderId)
            .add("message", "Created")
            .build();

        return ResponseEntity.status(HttpStatusCode, OK);
        
    }

    
}
