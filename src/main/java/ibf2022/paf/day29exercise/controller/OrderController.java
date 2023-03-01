package ibf2022.paf.day29exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.day29exercise.Utils;
import ibf2022.paf.day29exercise.model.Order;
import ibf2022.paf.day29exercise.service.OrderService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    @PostMapping(path="/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody String payload) {

        System.out.printf(">>> Payload: %s\n", payload);

        // from JSON (data interchange format) -> Order (domain entity)
        // Takes in the payload (String) and uses Utils.toJson to get a JsonObject
        JsonObject json = Utils.toJson(payload);

        // Takes in the JsonObject and uses Utils.toOrder to create the Order domain entity
        Order order = Utils.toOrder(json);

        // Passes Order domain entity to the order service and gets back a unique ID
        // It also calls the repo to insert into Mongo
        String orderId =orderSvc.insertOrder(order);

        // Create the response, append orderId send back to the request
        JsonObject resp = Json.createObjectBuilder()
            .add("orderId", orderId)
            .add("message", "Created")
            .build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(resp.toString());
        
    }

    
}
